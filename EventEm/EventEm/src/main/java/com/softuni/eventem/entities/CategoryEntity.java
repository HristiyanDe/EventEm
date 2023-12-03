package com.softuni.eventem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "categories", schema = "eventem")
public class CategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "category_name")
  private String categoryName;
  @ManyToMany(mappedBy = "eventCategories")
  private List<EventEntity> events;

  public CategoryEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  @Override
  public String toString() {
    return "CategoryEntity{" +
           "id=" + id +
           ", categoryName='" + categoryName + '\'' +
           ", events=" + events +
           '}';
  }
}
