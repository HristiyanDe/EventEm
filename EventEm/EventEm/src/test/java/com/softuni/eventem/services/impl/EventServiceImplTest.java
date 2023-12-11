package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.EventEntity;
import com.softuni.eventem.entities.request.EventRequest;
import com.softuni.eventem.exceptions.EventAlreadyExistsException;
import com.softuni.eventem.exceptions.VenueUnavailableBetweenDatesException;
import com.softuni.eventem.repositories.EventRepository;
import com.softuni.eventem.utils.EventFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.EVENT_ALREADY_EXISTS_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.VENUE_UNAVAILABLE_BETWEEN_DATES_ERROR_MESSAGE;
import static com.softuni.eventem.utils.EventConstants.EVENT_ORGANIZATION_ENTITY;
import static com.softuni.eventem.utils.EventConstants.EVENT_REQUEST_CATEGORIES;
import static com.softuni.eventem.utils.EventConstants.EVENT_VENUE_ENTITY;
import static com.softuni.eventem.utils.EventConstants.createEventCategories;
import static com.softuni.eventem.utils.EventConstants.createEventCategoriesRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {
  private EventRequest eventRequest;
  private EventEntity eventEntity;
  @Mock
  private ModelMapper modelMapper;
  @Mock
  private VenueServiceImpl venueService;
  @Mock
  private CategoryServiceImpl categoryService;
  @Mock
  private OrganizationServiceImpl organizationService;
  @Mock
  private EventRepository eventRepository;
  @InjectMocks
  private EventServiceImpl eventService;

  @Before
  public void setup(){
    eventRequest = EventFactory.getEventRequest();
    eventEntity = EventFactory.getEventEntity();
  }
  @Test
  public void testCreateEvent_success(){
    eventRequest.setCategories(createEventCategoriesRequest());
    when(categoryService.getCategoriesWithNames(any())).thenReturn(createEventCategories());
    when(venueService.getVenueById(any())).thenReturn(EVENT_VENUE_ENTITY);
    when(eventRepository.hasConflictingEventsByVenueAndDateRange(eventEntity.getVenue().getId(),eventEntity.getStartDate(),eventEntity.getEndDate()))
      .thenReturn(false);
    when(organizationService.getOrganizationById(eventRequest.getOrganizationId())).thenReturn(EVENT_ORGANIZATION_ENTITY);
    when(eventRepository.save(any(EventEntity.class))).thenReturn(eventEntity);

    EventEntity event = eventService.createEvent(eventRequest);
    System.out.println(eventEntity.getCategories());

    verify(categoryService, times(1)).getCategoriesWithNames(any());
    verify(venueService, times(1)).getVenueById(eventRequest.getVenueId());
    verify(eventRepository, times(1)).hasConflictingEventsByVenueAndDateRange(any(),any(),any());
    verify(organizationService, times(1)).getOrganizationById(eq(eventRequest.getOrganizationId()));
    verify(eventRepository, times(1)).save(any(EventEntity.class));
    assertEquals(10L,event.getId());



  }
  @Test
  public void testCreateEvent_ThrowsVenueUnavailableBetweenDatesException(){
    eventRequest.setCategories(createEventCategoriesRequest());
    when(categoryService.getCategoriesWithNames(any())).thenReturn(createEventCategories());
    when(venueService.getVenueById(any())).thenReturn(EVENT_VENUE_ENTITY);
    when(eventRepository.hasConflictingEventsByVenueAndDateRange(eventEntity.getVenue().getId(),eventEntity.getStartDate(),eventEntity.getEndDate()))
      .thenReturn(true);
    VenueUnavailableBetweenDatesException exception = assertThrows(VenueUnavailableBetweenDatesException.class, () ->
      eventService.createEvent(eventRequest));
    assertEquals(String.format(VENUE_UNAVAILABLE_BETWEEN_DATES_ERROR_MESSAGE,eventEntity.getVenue().getName(),
                               eventEntity.getStartDate(), eventEntity.getEndDate()),exception.getMessage());

    verify(categoryService, times(1)).getCategoriesWithNames(any());
    verify(venueService, times(1)).getVenueById(eventRequest.getVenueId());
    verify(eventRepository, times(1)).hasConflictingEventsByVenueAndDateRange(any(),any(),any());



  }
  @Test
  public void testCreateEvent_ThrowsEventAlreadyExistsException(){
    eventRequest.setCategories(createEventCategoriesRequest());
    when(categoryService.getCategoriesWithNames(any())).thenReturn(createEventCategories());
    when(venueService.getVenueById(any())).thenReturn(EVENT_VENUE_ENTITY);
    when(eventRepository.hasConflictingEventsByVenueAndDateRange(eventEntity.getVenue().getId(),eventEntity.getStartDate(),eventEntity.getEndDate()))
      .thenReturn(false);
    when(organizationService.getOrganizationById(eventRequest.getOrganizationId())).thenReturn(EVENT_ORGANIZATION_ENTITY);
    when(eventRepository.save(any(EventEntity.class))).thenThrow(DataIntegrityViolationException.class);

    EventAlreadyExistsException exception = assertThrows(EventAlreadyExistsException.class, () ->eventService.createEvent(eventRequest));
    assertEquals(EVENT_ALREADY_EXISTS_ERROR_MESSAGE, exception.getMessage());
    verify(categoryService, times(1)).getCategoriesWithNames(any());
    verify(venueService, times(1)).getVenueById(eventRequest.getVenueId());
    verify(eventRepository, times(1)).hasConflictingEventsByVenueAndDateRange(any(),any(),any());
    verify(organizationService, times(1)).getOrganizationById(eq(eventRequest.getOrganizationId()));
    verify(eventRepository, times(1)).save(any(EventEntity.class));



  }
}
