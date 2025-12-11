package com.softuni.eventempayment.services.impl;

import com.softuni.eventempayment.entities.PaymentEntity;
import com.softuni.eventempayment.entities.enums.PaymentStatusEnum;
import com.softuni.eventempayment.entities.requests.PaymentRequest;
import com.softuni.eventempayment.entities.responses.PaymentResponse;
import com.softuni.eventempayment.repositories.PaymentRepository;
import com.softuni.eventempayment.services.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;

  public PaymentServiceImpl(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  @Override
  public PaymentResponse createPayment(PaymentRequest request) {

    PaymentEntity payment = new PaymentEntity();

    payment.setTicketIds(request.ticketIds());
    payment.setUserId(request.userId());
    payment.setAmount(request.amount());
    payment.setStatus(PaymentStatusEnum.PENDING);
    payment.setCreatedAt(LocalDateTime.now());

    PaymentEntity saved = paymentRepository.save(payment);

    return mapToResponse(saved);
  }

  @Override
  public PaymentResponse confirmPayment(UUID id) {
    PaymentEntity payment = paymentRepository.findById(id)
                                             .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

    payment.setStatus(PaymentStatusEnum.CONFIRMED);
    payment.setConfirmedAt(LocalDateTime.now());

    PaymentEntity saved = paymentRepository.save(payment);

    return mapToResponse(saved);
  }

  @Override
  public PaymentResponse getPayment(UUID id) {
    PaymentEntity payment = paymentRepository.findById(id)
                                             .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

    return mapToResponse(payment);
  }

  private PaymentResponse mapToResponse(PaymentEntity entity) {
    return new PaymentResponse(
      entity.getId(),
      entity.getTicketIds(),
      entity.getAmount(),
      entity.getUserId(),
      entity.getStatus(),
      entity.getCreatedAt(),
      entity.getConfirmedAt()
    );
  }
}
