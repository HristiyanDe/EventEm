package com.softuni.eventem.services;

import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.dto.OrganizationDTO;
import com.softuni.eventem.entities.request.OrganizationRequest;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {
  OrganizationEntity createOrganization(OrganizationRequest organizationRequest);

  OrganizationEntity getOrganizationById(UUID organizationId);

  List<OrganizationDTO> getOrganizationsByUserId(UUID id);

  @Nullable List<OrganizationDTO> getOrganizations();
}
