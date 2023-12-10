package com.softuni.eventem.services;

import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.request.VenueRequest;

public interface VenueService {
  VenueEntity createVenue(VenueRequest venueRequest);

  VenueEntity getVenueById(Long id);
}
