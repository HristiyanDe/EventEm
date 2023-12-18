package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.TicketEntity;
import com.softuni.eventem.entities.request.PurchaseTicketRequest;
import com.softuni.eventem.services.PaymentService;
import com.softuni.eventem.services.TicketService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.softuni.eventem.constants.StripeConstants.PAYMENT_CANCEL_URL;
import static com.softuni.eventem.constants.StripeConstants.PAYMENT_SUCCESS_URL;

@Service
public class PaymentServiceImpl implements PaymentService {
  public static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
  @Value("${STRIPE_API_KEY}")
  private String API_KEY;

  private final TicketService ticketService;

  public PaymentServiceImpl(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @Transactional
  @Override
  public String createCheckout(List<PurchaseTicketRequest> ticketRequests) {
    try {
      Stripe.apiKey = API_KEY;
      SessionCreateParams sessionCreateParams =
        SessionCreateParams.builder()
                           .setMode(SessionCreateParams.Mode.PAYMENT)
                           .addAllLineItem(createLineItems(ticketRequests))

                           .setSuccessUrl(PAYMENT_SUCCESS_URL)
                           .setCancelUrl(PAYMENT_CANCEL_URL)
                           .build();
      Session session = Session.create(sessionCreateParams);
      return session.getUrl();
    }catch (StripeException e)
    {
    logger.error(e.getMessage());
    return "KMS";
    }
  }

  private List<SessionCreateParams.LineItem> createLineItems(List<PurchaseTicketRequest> ticketRequests) {
    List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
    ticketRequests = ticketRequests.stream().sorted(Comparator.comparing(PurchaseTicketRequest::getId)).toList();

    List<TicketEntity> ticketEntities = ticketService.getAllTicketEntitiesNamePriceAndIdByIds(
      ticketRequests.stream().map(PurchaseTicketRequest::getId).toList());
    for (TicketEntity t : ticketEntities) {
      for (PurchaseTicketRequest pt : ticketRequests) {
        if (Objects.equals(pt.getId(), t.getId())) {
          Long price = t.getPrice().multiply(BigDecimal.valueOf(100)).longValue();
          SessionCreateParams.LineItem lineItem =
            SessionCreateParams.LineItem.builder()
                                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                                           .setCurrency("usd")
                                                           .setUnitAmount(price)
                                                           .setProductData(
                                                               SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                  .setName(
                                                                  t.getName())
                                                                  .build())
                                                                                            .build())
                                        .setQuantity(pt.getQuantity()).build();
          lineItems.add(lineItem);
          break;
        }
      }
    }
    return lineItems;
  }
}
