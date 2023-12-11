package com.softuni.eventem.utils;

import com.softuni.eventem.entities.EventEntity;
import com.softuni.eventem.entities.request.EventRequest;

import static com.softuni.eventem.utils.EventConstants.EVENT_CURRENT_ATTENDEES;
import static com.softuni.eventem.utils.EventConstants.EVENT_DESCRIPTION;
import static com.softuni.eventem.utils.EventConstants.EVENT_END_DATE;
import static com.softuni.eventem.utils.EventConstants.EVENT_ID;
import static com.softuni.eventem.utils.EventConstants.EVENT_MAX_ATTENDEES;
import static com.softuni.eventem.utils.EventConstants.EVENT_NAME;
import static com.softuni.eventem.utils.EventConstants.EVENT_ORGANIZATION_ENTITY;
import static com.softuni.eventem.utils.EventConstants.EVENT_START_DATE;
import static com.softuni.eventem.utils.EventConstants.EVENT_STATUS_ENUM;
import static com.softuni.eventem.utils.EventConstants.EVENT_VENUE_ENTITY;
import static com.softuni.eventem.utils.OrganizationConstants.ORGANIZATION_ID;
import static com.softuni.eventem.utils.VenueConstants.VENUE_ID;

public final class EventFactory {
  public static EventEntity getEventEntity(){
    EventEntity eventEntity = new EventEntity();
    eventEntity.setId(EVENT_ID);
    eventEntity.setName(EVENT_NAME);
    eventEntity.setStartDate(EVENT_START_DATE);
    eventEntity.setEndDate(EVENT_END_DATE);
    eventEntity.setEventStatus(EVENT_STATUS_ENUM);
    eventEntity.setVenue(EVENT_VENUE_ENTITY);
    eventEntity.setOrganization(EVENT_ORGANIZATION_ENTITY);
    eventEntity.setDescription(EVENT_DESCRIPTION);
    eventEntity.setCurrentAttendees(EVENT_CURRENT_ATTENDEES);
    eventEntity.setMaxAttendees(EVENT_MAX_ATTENDEES);
    return eventEntity;
  }
  public static EventRequest getEventRequest(){
    EventRequest eventRequest = new EventRequest();
    eventRequest.setName(EVENT_NAME);
    eventRequest.setStartDate(EVENT_START_DATE);
    eventRequest.setEndDate(EVENT_END_DATE);
    eventRequest.setEventStatus(EVENT_STATUS_ENUM);
    eventRequest.setVenueId(VENUE_ID);
    eventRequest.setOrganizationId(ORGANIZATION_ID);
    eventRequest.setDescription(EVENT_DESCRIPTION);
    eventRequest.setMaxAttendees(EVENT_MAX_ATTENDEES);
    return eventRequest;
  }

}
