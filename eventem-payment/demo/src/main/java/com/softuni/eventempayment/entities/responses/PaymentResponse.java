package com.softuni.eventempayment.entities.responses;

import com.softuni.eventempayment.entities.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PaymentResponse(
  UUID id,
  List<UUID> ticketIds,
  BigDecimal amount,
  UUID userId,
  PaymentStatusEnum status,
  LocalDateTime createdAt,
  LocalDateTime confirmedAt
) {}
