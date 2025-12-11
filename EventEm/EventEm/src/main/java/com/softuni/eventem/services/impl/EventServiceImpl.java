package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.CategoryEntity;
import com.softuni.eventem.entities.EventEntity;
import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.UserDetailsImpl;
import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.enums.UserRoleEnum;
import com.softuni.eventem.entities.request.CategoryRequest;
import com.softuni.eventem.entities.request.EventRequest;
import com.softuni.eventem.exceptions.EventAlreadyExistsException;
import com.softuni.eventem.exceptions.NoMatchingCategoriesWithNamesFoundException;
import com.softuni.eventem.exceptions.UserUnauthorizedException;
import com.softuni.eventem.exceptions.VenueUnavailableBetweenDatesException;
import com.softuni.eventem.repositories.CategoryRepository;
import com.softuni.eventem.repositories.EventRepository;
import com.softuni.eventem.repositories.projection.EventListDTO;
import com.softuni.eventem.services.CategoryService;
import com.softuni.eventem.services.EventService;
import com.softuni.eventem.services.OrganizationService;
import com.softuni.eventem.services.VenueService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.ENTITY_ALREADY_EXISTS_ERROR;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.EVENT_ALREADY_EXISTS_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.EVENT_CREATED_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.NO_MATCHING_CATEGORIES_FOUND_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.USER_LACKS_AUTHORITY_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.VENUE_UNAVAILABLE_BETWEEN_DATES_ERROR_MESSAGE;

@Service
public class EventServiceImpl implements EventService {

  public static final Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);

  private final ModelMapper modelMapper;
  private final VenueService venueService;
  private final CategoryService categoryService;
  private final OrganizationService organizationService;
  private final EventRepository eventRepository;

  public EventServiceImpl(
    ModelMapper modelMapper, VenueService venueService, CategoryService categoryService,
    OrganizationService organizationService,
    EventRepository eventRepository) {
    this.modelMapper = modelMapper;
    this.venueService = venueService;
    this.categoryService = categoryService;
    this.organizationService = organizationService;
    this.eventRepository = eventRepository;
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
      List<CategoryEntity> categories = categoryService.getCategoriesWithNames(
        eventRequest
          .getCategories()
          .stream()
          .map(CategoryRequest::getCategoryName)
          .collect(
            Collectors.toList()));
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
      logger.info(String.format(EVENT_CREATED_MESSAGE, eventEntity));
      return eventEntity;
    } catch (DataIntegrityViolationException e) {
      logger.error(String.format(ENTITY_ALREADY_EXISTS_ERROR, eventRequest));
      throw new EventAlreadyExistsException(EVENT_ALREADY_EXISTS_ERROR_MESSAGE);
    }
  }

  @Override
  public List<EventListDTO> findEventsByName(String name) {
    UserDetailsImpl user =(UserDetailsImpl) Objects.requireNonNull(
      SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
    System.out.println(user.getRole());
    if (!(user != null && user.getRole().equals(UserRoleEnum.ADMIN))) {
      throw new UserUnauthorizedException(String.format(USER_LACKS_AUTHORITY_ERROR_MESSAGE, user.getUserId()));
    }
    return eventRepository.findByNameContaining(name).stream().toList();
  }

  private boolean hasConflictingEventByVenueAndDateRange(EventEntity eventEntity) {
    return eventRepository.hasConflictingEventsByVenueAndDateRange(eventEntity.getVenue().getId(),
                                                                   eventEntity.getStartDate(),
                                                                   eventEntity.getEndDate());
  }
}
