package com.softuni.eventem.services;

import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.dto.VenueDTO;
import com.softuni.eventem.entities.request.VenueRequest;

import java.util.List;
import java.util.UUID;

public interface VenueService {
  VenueEntity createVenue(VenueRequest venueRequest);

  VenueEntity getVenueById(UUID id);

  List<VenueDTO> getFilteredVenues(String venueName, String venueAddress, String venueCity);

  List<VenueDTO> getAllVenues();
}
