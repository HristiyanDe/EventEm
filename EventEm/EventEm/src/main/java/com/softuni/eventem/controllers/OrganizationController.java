package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.OrganizationRequest;
import com.softuni.eventem.services.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
  private final OrganizationService organizationService;

  public OrganizationController(OrganizationService organizationService) {
    this.organizationService = organizationService;
  }
  @PostMapping
  public ResponseEntity<Void> createOrganization(@RequestBody @Valid OrganizationRequest organizationRequest)
  {
    return ResponseEntity.created(
                           UriComponentsBuilder
                             .fromUriString("/{id}")
                             .buildAndExpand(
                               organizationService
                                 .createOrganization(organizationRequest)
                                 .getId())
                             .toUri())
                         .build();
  }
}
