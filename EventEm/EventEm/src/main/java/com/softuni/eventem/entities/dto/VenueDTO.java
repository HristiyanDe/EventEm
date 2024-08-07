package com.softuni.eventem.entities.dto;

public class VenueDTO {
  private Long id;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
