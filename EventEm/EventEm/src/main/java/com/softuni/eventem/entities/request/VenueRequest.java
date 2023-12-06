package com.softuni.eventem.entities.request;

import jakarta.validation.constraints.NotBlank;

public class VenueRequest {
  @NotBlank(message = "Venue name must not be empty")
  private String name;
  @NotBlank(message = "Venue city name must not be empty")
  private String city;
  @NotBlank(message = "Venue address must not be empty")
  private String address;

  public VenueRequest() {
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

  @Override
  public String toString() {
    return "VenueRequest{" +
           "name='" + name + '\'' +
           ", city='" + city + '\'' +
           ", address='" + address + '\'' +
           '}';
  }
}
