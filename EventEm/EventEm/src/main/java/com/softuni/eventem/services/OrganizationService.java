package com.softuni.eventem.services;

import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.request.OrganizationRequest;

public interface OrganizationService {
  OrganizationEntity createOrganization(OrganizationRequest organizationRequest);

}
