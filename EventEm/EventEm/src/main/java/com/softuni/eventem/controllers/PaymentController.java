package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.PurchaseTicketRequest;
import com.softuni.eventem.services.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//TODO: Transaction, Payment Entities to store sales information(incl Stripe session's status),
// along ith a webhook to update event attendees, transaction status. Stripe error handling.
@RestController
@RequestMapping("/api/checkout")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Payment Controller", description = "The Payment API. Contains all the operations that can be performed on a payment")
public class PaymentController {
  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping
  public ResponseEntity<String> startCheckout(@RequestBody List<PurchaseTicketRequest> ticketRequests)
  {
    return ResponseEntity.ok(paymentService.createCheckout(ticketRequests));
  }
  @GetMapping("/success")
  public ResponseEntity<String> successfulCheckout(){
    return ResponseEntity.ok("YAY");
  }
  @GetMapping("/cancel")
  public ResponseEntity<String> cancelCheckout(){
    return ResponseEntity.ok("NAY");
  }


}
