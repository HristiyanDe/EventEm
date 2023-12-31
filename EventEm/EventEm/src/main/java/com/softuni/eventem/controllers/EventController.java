package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.EventRequest;
import com.softuni.eventem.entities.request.OrganizationRequest;
import com.softuni.eventem.services.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/events")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Event Controller", description = "The Event API. Contains all the operations that can be performed on an event")
public class EventController {
  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Void> createOrganization(@RequestBody @Valid EventRequest eventRequest)
  {
    return ResponseEntity.created(
                           UriComponentsBuilder
                             .fromUriString("/{id}")
                             .buildAndExpand(
                               eventService
                                 .createEvent(eventRequest)
                                 .getId())
                             .toUri())
                         .build();
  }
}
