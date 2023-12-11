package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.EventRequest;
import com.softuni.eventem.entities.request.OrganizationRequest;
import com.softuni.eventem.services.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/events")
public class EventController {
  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @PostMapping
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
