package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.TicketRequest;
import com.softuni.eventem.services.TicketService;
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
@RequestMapping("/api/tickets")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Ticket Controller", description = "The Ticket API. Contains all the operations that can be performed on a ticket")
public class TicketController {
  private final TicketService ticketService;

  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Void> createTicket(@RequestBody @Valid TicketRequest ticketRequest)
  {
    return ResponseEntity.created(
                           UriComponentsBuilder
                             .fromUriString("/{id}")
                             .buildAndExpand(
                               ticketService
                                 .createTicket(ticketRequest)
                                 .getId())
                             .toUri())
                         .build();
  }

}
