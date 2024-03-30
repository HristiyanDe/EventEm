package com.softuni.eventem.entities.dto;

public class OrganizationDTO {
  private Long id;
  private String name;
  private String city;
  private String address;
  private String phone;
  private String email;

  public OrganizationDTO() {
  }

  public OrganizationDTO(Long id, String name, String city, String address, String phone, String email) {
    this.id = id;
    this.name = name;
    this.city = city;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
}
