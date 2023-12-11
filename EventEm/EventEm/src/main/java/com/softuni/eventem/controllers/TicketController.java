package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.TicketRequest;
import com.softuni.eventem.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
  private final TicketService ticketService;

  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @PostMapping
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
