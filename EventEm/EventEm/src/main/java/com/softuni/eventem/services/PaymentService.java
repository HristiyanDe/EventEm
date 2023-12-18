package com.softuni.eventem.services;

import com.softuni.eventem.entities.request.PurchaseTicketRequest;
import com.stripe.exception.StripeException;

import java.util.List;

public interface PaymentService {
 String createCheckout(List<PurchaseTicketRequest> ticketRequests);
}
