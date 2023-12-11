package com.softuni.eventem.utils;

import com.softuni.eventem.entities.CategoryEntity;
import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.enums.EventStatusEnum;
import com.softuni.eventem.entities.request.CategoryRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class EventConstants {
  public static final Long EVENT_ID = 10L;
  public static final String EVENT_NAME = "Test Event Name";
  public static final LocalDate EVENT_START_DATE = LocalDate.of(2000,12,10);
  public static final LocalDate EVENT_END_DATE = LocalDate.of(2000,12,20);
  public static final EventStatusEnum EVENT_STATUS_ENUM = EventStatusEnum.UPCOMING;
  public static final VenueEntity EVENT_VENUE_ENTITY = VenueFactory.getVenueEntity();
  public static final OrganizationEntity EVENT_ORGANIZATION_ENTITY = OrganizationFactory.getOrganizationEntity();
  public static final String EVENT_DESCRIPTION = "Test Event Description";
  public static final Integer EVENT_CURRENT_ATTENDEES = 0;
  public static final Integer EVENT_MAX_ATTENDEES = 100;
  public static final List<CategoryRequest> EVENT_REQUEST_CATEGORIES = createEventCategoriesRequest();

  public static List<CategoryEntity> createEventCategories() {
    List<CategoryEntity> categories = new ArrayList<>();
    CategoryEntity categoryEntity = CategoryFactory.getCategoryEntity();
    categories.add(categoryEntity);
    return categories;
  }
  public static List<CategoryRequest> createEventCategoriesRequest(){
    List<CategoryRequest> categories = new ArrayList<>();
    CategoryRequest categoryRequest = CategoryFactory.getCategoryRequest();
    categories.add(categoryRequest);
    return categories;
  }
}
