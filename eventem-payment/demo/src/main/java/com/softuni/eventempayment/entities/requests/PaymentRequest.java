package com.softuni.eventempayment.entities.requests;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PaymentRequest(
  List<UUID> ticketIds,
  BigDecimal amount,
  UUID userId
) {}
