package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.CategoryEntity;
import com.softuni.eventem.entities.EventEntity;
import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.request.CategoryRequest;
import com.softuni.eventem.entities.request.EventRequest;
import com.softuni.eventem.exceptions.EventAlreadyExistsException;
import com.softuni.eventem.exceptions.NoMatchingCategoriesWithNamesFoundException;
import com.softuni.eventem.exceptions.VenueUnavailableBetweenDatesException;
import com.softuni.eventem.repositories.CategoryRepository;
import com.softuni.eventem.repositories.EventRepository;
import com.softuni.eventem.services.EventService;
import com.softuni.eventem.services.OrganizationService;
import com.softuni.eventem.services.VenueService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.ENTITY_ALREADY_EXISTS_ERROR;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.EVENT_ALREADY_EXISTS_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.EVENT_CREATED_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.NO_MATCHING_CATEGORIES_FOUND_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.VENUE_UNAVAILABLE_BETWEEN_DATES_ERROR_MESSAGE;

@Service
public class EventServiceImpl implements EventService {

  public static final Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);

  private final ModelMapper modelMapper;
  private final VenueService venueService;
  private final OrganizationService organizationService;
  private final EventRepository eventRepository;
  private final CategoryRepository categoryRepository;

  public EventServiceImpl(
    ModelMapper modelMapper, VenueService venueService, OrganizationService organizationService,
    EventRepository eventRepository,
    CategoryRepository categoryRepository) {
    this.modelMapper = modelMapper;
    this.venueService = venueService;
    this.organizationService = organizationService;
    this.eventRepository = eventRepository;
    this.categoryRepository = categoryRepository;
  }

  @Transactional
  @Override
  public EventEntity createEvent(EventRequest eventRequest) {
    try {
      EventEntity eventEntity = new EventEntity();
      eventEntity.setName(eventRequest.getName());
      eventEntity.setDescription(eventRequest.getDescription());
      eventEntity.setCurrentAttendees(0);
      eventEntity.setStartDate(eventRequest.getStartDate());
      eventEntity.setEndDate(eventRequest.getEndDate());
      eventEntity.setEventStatus(eventRequest.getEventStatus());
      eventEntity.setMaxAttendees(eventRequest.getMaxAttendees());
      List<CategoryEntity> categories = categoryRepository.findByCategoryNameIn(
                                                            eventRequest.getCategories().stream().map(CategoryRequest::getCategoryName).collect(
                                                              Collectors.toList()))
                                                          .orElseThrow(
                                                            () ->
                                                              new NoMatchingCategoriesWithNamesFoundException(
                                                                NO_MATCHING_CATEGORIES_FOUND_ERROR_MESSAGE));
      eventEntity.setCategories(categories);
      VenueEntity venue = venueService.getVenueById(eventRequest.getVenueId());
      eventEntity.setVenue(venue);
      if (hasConflictingEventByVenueAndDateRange(eventEntity)) {
        throw new VenueUnavailableBetweenDatesException(
          String.format(VENUE_UNAVAILABLE_BETWEEN_DATES_ERROR_MESSAGE, eventEntity.getVenue().getName(),
                        eventEntity.getStartDate(), eventEntity.getEndDate()));
      }
      OrganizationEntity organization = organizationService.getOrganizationById(eventRequest.getOrganizationId());
      eventEntity.setOrganization(organization);
      eventEntity = eventRepository.save(eventEntity);
      logger.info(String.format(EVENT_CREATED_MESSAGE,eventEntity));
      return eventEntity;
    }catch (DataIntegrityViolationException e){
      logger.error(String.format(ENTITY_ALREADY_EXISTS_ERROR,eventRequest));
      throw new EventAlreadyExistsException(EVENT_ALREADY_EXISTS_ERROR_MESSAGE);


    }
  }

  private boolean hasConflictingEventByVenueAndDateRange(EventEntity eventEntity) {
    return eventRepository.hasConflictingEventsByVenueAndDateRange(eventEntity.getVenue().getId(),
                                                                   eventEntity.getStartDate(),
                                                                   eventEntity.getEndDate());
  }
}
