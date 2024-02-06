package com.softuni.eventem.services;

import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.dto.VenueDTO;
import com.softuni.eventem.entities.request.VenueRequest;

import java.util.List;

public interface VenueService {
  VenueEntity createVenue(VenueRequest venueRequest);

  VenueEntity getVenueById(Long id);

  List<VenueDTO> getFilteredVenues(String venueName, String venueAddress, String venueCity);
}
