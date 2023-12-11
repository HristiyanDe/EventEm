package com.softuni.eventem.entities.request;

import java.math.BigDecimal;

public class TicketRequest {
private String name;
private Long eventId;
private BigDecimal price;

  public TicketRequest() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getEventId() {
    return eventId;
  }

  public void setEventId(Long eventId) {
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
    return "TicketRequest{" +
           "name='" + name + '\'' +
           ", eventId=" + eventId +
           ", price=" + price +
           '}';
  }
}
