package com.softuni.eventem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tickets", schema = "eventem")
public class TicketEntity {

  @Id
  @UuidGenerator(style= UuidGenerator.Style.RANDOM)
  @Column(name = "id")
  private UUID id;
  @Column(name = "name")
  private String name;
  @Column(name = "event_id")
  private UUID eventId;
  @Column(name = "price")
  private BigDecimal price;

  public TicketEntity() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getEventId() {
    return eventId;
  }

  public void setEventId(UUID eventId) {
    this.eventId = eventId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "TicketEntity{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", eventId=" + eventId +
           ", price=" + price +
           '}';
  }
}
