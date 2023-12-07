package com.softuni.eventem.entities.request;

import jakarta.validation.constraints.NotBlank;

public class OrganizationRequest {
  @NotBlank(message = "Organization name must not be empty")
  private String name;
  @NotBlank(message = "Organization city must not be empty")
  private String city;
  @NotBlank(message = "Organization address must not be empty")
  private String address;
  @NotBlank(message = "Organization phone must not be empty")
  private String phone;
  @NotBlank(message = "Organization email must not be empty")
  private String email;

  public OrganizationRequest() {
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "OrganizationRequest{" +
           "name='" + name + '\'' +
           ", city='" + city + '\'' +
           ", address='" + address + '\'' +
           ", phone='" + phone + '\'' +
           ", email='" + email + '\'' +
           '}';
  }
}
