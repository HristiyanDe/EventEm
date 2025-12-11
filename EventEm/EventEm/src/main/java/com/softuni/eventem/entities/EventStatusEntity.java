package com.softuni.eventem.entities;

import com.softuni.eventem.entities.enums.EventStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "event_status",schema = "eventem")
public class EventStatusEntity {
  @Id
  @UuidGenerator(style= UuidGenerator.Style.RANDOM)
  @Column(name = "id")
  private UUID id;

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

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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
