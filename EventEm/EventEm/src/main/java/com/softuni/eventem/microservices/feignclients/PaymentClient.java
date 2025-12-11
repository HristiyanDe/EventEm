package com.softuni.eventem.microservices.feignclients;

import com.softuni.eventem.microservices.dtos.requests.PaymentRequest;
import com.softuni.eventem.microservices.dtos.responses.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(
  name = "payment-service",
  url = "${feign.payment.url}"
  )
public interface PaymentClient {
  @PostMapping("/payments")
  PaymentResponse createPayment(@RequestBody PaymentRequest request);

  @PutMapping("/payments/{id}/confirm")
  PaymentResponse confirmPayment(@PathVariable UUID id);

  @GetMapping("/payments/{id}")
  PaymentResponse getPayment(@PathVariable UUID id);
}
