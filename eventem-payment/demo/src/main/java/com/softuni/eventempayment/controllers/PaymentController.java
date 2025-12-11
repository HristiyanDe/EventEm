package com.softuni.eventempayment.controllers;

import com.softuni.eventempayment.entities.requests.PaymentRequest;
import com.softuni.eventempayment.entities.responses.PaymentResponse;
import com.softuni.eventempayment.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {
  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping
  public PaymentResponse createPayment(@RequestBody @Valid PaymentRequest request) {
    return paymentService.createPayment(request);
  }

  @PutMapping("/{id}/confirm")
  public PaymentResponse confirmPayment(@PathVariable UUID id) {
    return paymentService.confirmPayment(id);
  }

  @GetMapping("/{id}")
  public PaymentResponse getPayment(@PathVariable UUID id) {
    return paymentService.getPayment(id);
  }

}
