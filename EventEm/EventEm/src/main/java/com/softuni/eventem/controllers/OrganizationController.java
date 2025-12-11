package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.dto.OrganizationDTO;
import com.softuni.eventem.entities.request.OrganizationRequest;
import com.softuni.eventem.services.OrganizationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Organization Controller", description = "The Organization API. Contains all the operations that can be performed on an organization")
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
  @GetMapping
  public ResponseEntity<List<OrganizationDTO>> getOrganizations()
  {
    return ResponseEntity.ok(organizationService.getOrganizations());
  }
}
