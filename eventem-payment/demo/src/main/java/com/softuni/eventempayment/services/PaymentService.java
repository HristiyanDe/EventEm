package com.softuni.eventempayment.services;

import com.softuni.eventempayment.entities.requests.PaymentRequest;
import com.softuni.eventempayment.entities.responses.PaymentResponse;

import java.util.UUID;

public interface PaymentService {

  PaymentResponse createPayment(PaymentRequest request);

  PaymentResponse confirmPayment(UUID id);

  PaymentResponse getPayment(UUID id);
}
