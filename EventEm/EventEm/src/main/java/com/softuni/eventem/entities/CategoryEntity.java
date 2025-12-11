package com.softuni.eventem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories", schema = "eventem")
public class CategoryEntity {

  @Id
  @UuidGenerator(style= UuidGenerator.Style.RANDOM)
  @Column(name = "id")
  private UUID id;
  @Column(name = "category_name")
  private String categoryName;
  @ManyToMany(mappedBy = "categories")
  private List<EventEntity> events;

  public CategoryEntity() {
  }

  public CategoryEntity(String categoryName) {
    this.categoryName = categoryName;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public List<EventEntity> getEvents() {
    return events;
  }

  public void setEvents(List<EventEntity> events) {
    this.events = events;
  }


}
