package com.softuni.eventem.entities.request;

public class PurchaseTicketRequest {
  private Long id;
  private Long quantity;

  public PurchaseTicketRequest() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
}
