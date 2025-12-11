package com.softuni.eventem.entities;

import com.softuni.eventem.entities.enums.EventStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events", schema = "eventem")
public class EventEntity {
  @Id
  @UuidGenerator(style= UuidGenerator.Style.RANDOM)
  @Column(name = "id")
  private UUID id;
  @Column(name = "name")
  private String name;
  @Column(name = "start_date")
  private LocalDate startDate;
  @Column(name = "end_date")
  private LocalDate endDate;
  @Enumerated(EnumType.STRING)
  @Column(name = "event_status")
  private EventStatusEnum eventStatus;
  @ManyToOne
  @JoinColumn(name = "venue_id")
  private VenueEntity venue;
  @ManyToOne
  @JoinColumn(name = "organization_id")
  private OrganizationEntity organization;
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

  public EventStatusEnum getEventStatus() {
    return eventStatus;
  }

  public void setEventStatus(EventStatusEnum eventStatus) {
    this.eventStatus = eventStatus;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public VenueEntity getVenue() {
    return venue;
  }

  public void setVenue(VenueEntity venue) {
    this.venue = venue;
  }

  public OrganizationEntity getOrganization() {
    return organization;
  }

  public void setOrganization(OrganizationEntity organization) {
    this.organization = organization;
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
           ", venue=" + venue +
           ", organization=" + organization +
           ", description='" + description + '\'' +
           ", currentAttendees=" + currentAttendees +
           ", maxAttendees=" + maxAttendees +
           ", categories=" + categories +
           '}';
  }
}
