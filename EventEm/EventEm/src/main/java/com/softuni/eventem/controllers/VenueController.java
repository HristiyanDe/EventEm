package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.dto.VenueDTO;
import com.softuni.eventem.entities.request.VenueRequest;
import com.softuni.eventem.services.VenueService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Venue Controller", description = "The Venue API. Contains all the operations that can be performed on a venue")
public class VenueController {

  private final VenueService venueService;

  public VenueController(VenueService venueService) {
    this.venueService = venueService;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
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
  @GetMapping
  public ResponseEntity<List<VenueDTO>> getVenues(@RequestParam(value = "venueName", required = false) String venueName,
                                                  @RequestParam(value = "venueAddress", required = false) String venueAddress,
                                                  @RequestParam(value = "venueCity", required = false) String venueCity){
  return ResponseEntity.ok(venueService.getFilteredVenues(venueName,venueAddress,venueCity));
  }
}
