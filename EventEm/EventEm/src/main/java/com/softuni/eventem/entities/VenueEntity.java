package com.softuni.eventem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "venues", schema = "eventem")
public class VenueEntity {

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

  public VenueEntity() {
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

  @Override
  public String toString() {
    return "VenueEntity{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", city='" + city + '\'' +
           ", address='" + address + '\'' +
           '}';
  }
}
