package com.softuni.eventempayment.entities;

import com.softuni.eventempayment.entities.enums.PaymentStatusEnum;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payments", schema = "eventempayment")
public class PaymentEntity {
  @Id
  @UuidGenerator(style= UuidGenerator.Style.RANDOM)
  @Column(name = "id")
  private UUID id;
  @ElementCollection
  @CollectionTable(
    name = "payment_ticket_ids",
    joinColumns = @JoinColumn(name = "payment_id")
  )
  @Column(name = "ticket_id", nullable = false)
  private List<UUID> ticketIds = new ArrayList<>();

  @Column(nullable = false, precision = 19, scale = 2)
  private BigDecimal amount;

  @Column
  private UUID userId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentStatusEnum status;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  private LocalDateTime confirmedAt;

  public PaymentEntity() {}

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<UUID> getTicketIds() {
    return ticketIds;
  }

  public void setTicketIds(List<UUID> ticketIds) {
    this.ticketIds = ticketIds;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public PaymentStatusEnum getStatus() {
    return status;
  }

  public void setStatus(PaymentStatusEnum status) {
    this.status = status;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getConfirmedAt() {
    return confirmedAt;
  }

  public void setConfirmedAt(LocalDateTime confirmedAt) {
    this.confirmedAt = confirmedAt;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }
}
