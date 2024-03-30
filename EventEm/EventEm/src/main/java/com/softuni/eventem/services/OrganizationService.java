package com.softuni.eventem.services;

import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.dto.OrganizationDTO;
import com.softuni.eventem.entities.request.OrganizationRequest;

import java.util.List;

public interface OrganizationService {
  OrganizationEntity createOrganization(OrganizationRequest organizationRequest);

  OrganizationEntity getOrganizationById(Long organizationId);

  List<OrganizationDTO> getOrganizationsByUserId(Long id);
}
