package com.softuni.eventem.entities.dto;

import java.util.UUID;

public class VenueDTO {
  private UUID id;
  private String name;
  private String city;
  private String address;

  public VenueDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "VenueDTO{" +
           "name='" + name + '\'' +
           ", city='" + city + '\'' +
           ", address='" + address + '\'' +
           '}';
  }
}
