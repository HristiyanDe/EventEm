package com.softuni.entities;

import com.softuni.entities.enums.EventStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_status",schema = "eventem")
public class EventStatusEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "status_name")
  @Enumerated(EnumType.STRING)
  private EventStatusEnum statusName;

  public EventStatusEntity() {
  }

  public EventStatusEnum getStatusName() {
    return statusName;
  }

  public void setStatusName(EventStatusEnum statusName) {
    this.statusName = statusName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "EventStatusEntity{" +
           "id=" + id +
           ", statusName=" + statusName +
           '}';
  }
}
