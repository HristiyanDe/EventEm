package com.softuni.eventem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "events", schema = "eventem")
public class EventEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "start_date")
  private Timestamp startDate;
  @Column(name = "end_date")
  private Timestamp endDate;
  @ManyToOne
  @JoinColumn(name = "event_status")
  private EventStatusEntity eventStatus;
  @Column(name = "venue_id")
  private Long venueId;
  @Column(name = "organization_id")
  private Long organizationId;
  @Column(name = "description")
  private String description;
  @Column(name = "current_attendees")
  private Integer currentAttendees;
  @Column(name = "max_attendees")
  private Integer maxAttendees;

  @ManyToMany
  @JoinTable(
    name = "events_categories",
    joinColumns = @JoinColumn(name = "event_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private List<CategoryEntity> categories;

  public EventEntity() {
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

  public Timestamp getStartDate() {
    return startDate;
  }

  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }

  public Timestamp getEndDate() {
    return endDate;
  }

  public void setEndDate(Timestamp endDate) {
    this.endDate = endDate;
  }

  public EventStatusEntity getEventStatus() {
    return eventStatus;
  }

  public void setEventStatus(EventStatusEntity eventStatus) {
    this.eventStatus = eventStatus;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getVenueId() {
    return venueId;
  }

  public void setVenueId(Long venueId) {
    this.venueId = venueId;
  }

  public Long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Long userId) {
    this.organizationId = userId;
  }

  public List<CategoryEntity> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoryEntity> categories) {
    this.categories = categories;
  }

  public Integer getCurrentAttendees() {
    return currentAttendees;
  }

  public void setCurrentAttendees(Integer currentAttendees) {
    this.currentAttendees = currentAttendees;
  }

  public Integer getMaxAttendees() {
    return maxAttendees;
  }

  public void setMaxAttendees(Integer maxAttendees) {
    this.maxAttendees = maxAttendees;
  }

  @Override
  public String toString() {
    return "EventEntity{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", startDate=" + startDate +
           ", endDate=" + endDate +
           ", eventStatus=" + eventStatus +
           ", venueId=" + venueId +
           ", organizationId=" + organizationId +
           ", description='" + description + '\'' +
           ", currentAttendees=" + currentAttendees +
           ", maxAttendees=" + maxAttendees +
           ", categories=" + categories +
           '}';
  }
}
