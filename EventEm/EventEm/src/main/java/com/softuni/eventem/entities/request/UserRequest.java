package com.softuni.eventem.entities.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequest {
  @NotBlank(message = "First name can't be empty")
  private String firstName;
  @NotBlank(message = "Last name can't be empty")
  private String lastName;
  @NotBlank(message = "City name can't be empty")
  private String city;
  @NotBlank(message = "Address can't be empty")
  private String address;
  @NotNull
  @Pattern(regexp = "[0-9]{10}",message = "Phone number can't be empty")
  private String phone;
  @Email(message = "Email address can't be empty")
  private String email;

  public UserRequest() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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
}
