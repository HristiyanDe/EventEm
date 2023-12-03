package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.VenueRequest;
import com.softuni.eventem.services.VenueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

  private VenueService venueService;

  public VenueController(VenueService venueService) {
    this.venueService = venueService;
  }

  @PostMapping
  public ResponseEntity<Void> createVenue(@RequestBody @Valid VenueRequest venueRequest) {
    return ResponseEntity.created(
                           UriComponentsBuilder
                             .fromUriString("/{id}")
                             .buildAndExpand(
                               venueService
                                 .createVenue(venueRequest)
                                 .getId())
                             .toUri())
                         .build();
  }
}
