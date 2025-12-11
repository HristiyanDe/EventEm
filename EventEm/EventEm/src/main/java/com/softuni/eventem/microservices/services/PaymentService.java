package com.softuni.eventem.microservices.services;


import com.softuni.eventem.microservices.dtos.requests.PaymentRequest;
import com.softuni.eventem.microservices.dtos.responses.PaymentResponse;
import com.softuni.eventem.microservices.feignclients.PaymentClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
  private final PaymentClient paymentClient;

  public PaymentService(PaymentClient paymentClient) {
    this.paymentClient = paymentClient;
  }
  public PaymentResponse initiatePayment(List<UUID> ticketIds, BigDecimal amount, UUID userId) {

    PaymentRequest request = new PaymentRequest(ticketIds, amount, userId);

    return paymentClient.createPayment(request);
  }
  public PaymentResponse confirmPayment(UUID paymentId) {
    return paymentClient.confirmPayment(paymentId);
  }
  public PaymentResponse getPaymentStatus(UUID paymentId) {
    return paymentClient.getPayment(paymentId);
  }
}
