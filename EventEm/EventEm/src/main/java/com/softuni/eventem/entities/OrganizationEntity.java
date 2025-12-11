package com.softuni.eventem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "organizations")
public class OrganizationEntity {
  @Id
  @UuidGenerator(style= UuidGenerator.Style.RANDOM)
  @Column(name = "id")
  private UUID id;
  @Column(name = "name")
  private String name;
  @Column(name = "city")
  private String city;
  @Column(name = "address")
  private String address;
  @Column(name = "phone")
  private String phone;
  @Column(name = "email")
  private String email;
  @ManyToMany(mappedBy = "organizations")
  List<UserDetailsImpl> users;

  public OrganizationEntity() {
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
    return "OrganizationEntity{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", city='" + city + '\'' +
           ", address='" + address + '\'' +
           ", phone='" + phone + '\'' +
           ", email='" + email + '\'' +
           '}';
  }
}
