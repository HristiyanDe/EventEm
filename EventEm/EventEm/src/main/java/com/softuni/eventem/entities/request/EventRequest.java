package com.softuni.eventem.entities.request;

import com.softuni.eventem.entities.enums.EventStatusEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class EventRequest {
  private String name;
  private EventStatusEnum eventStatus;
  private LocalDate startDate;
  private LocalDate endDate;
  private UUID venueId;
  private UUID organizationId;
  private String description;
  private Integer maxAttendees;
  private List<CategoryRequest> categories;

  public EventRequest() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EventStatusEnum getEventStatus() {
    return eventStatus;
  }

  public void setEventStatus(EventStatusEnum eventStatus) {
    this.eventStatus = eventStatus;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public UUID getVenueId() {
    return venueId;
  }

  public void setVenueId(UUID venueId) {
    this.venueId = venueId;
  }

  public UUID getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(UUID organizationId) {
    this.organizationId = organizationId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getMaxAttendees() {
    return maxAttendees;
  }

  public void setMaxAttendees(Integer maxAttendees) {
    this.maxAttendees = maxAttendees;
  }

  public List<CategoryRequest> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoryRequest> categories) {
    this.categories = categories;
  }

  @Override
  public String toString() {
    return "EventRequest{" +
           "name='" + name + '\'' +
           ", eventStatus=" + eventStatus +
           ", startDate=" + startDate +
           ", endDate=" + endDate +
           ", venueId=" + venueId +
           ", organizationId=" + organizationId +
           ", description='" + description + '\'' +
           ", maxAttendees=" + maxAttendees +
           ", categories=" + categories +
           '}';
  }
}
