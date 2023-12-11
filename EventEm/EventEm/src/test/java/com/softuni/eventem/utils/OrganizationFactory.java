package com.softuni.eventem.utils;

import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.request.OrganizationRequest;
import static com.softuni.eventem.utils.OrganizationConstants.*;


public final class OrganizationFactory {
  public static OrganizationRequest getOrganizationRequest(){
    OrganizationRequest organizationRequest = new OrganizationRequest();
    organizationRequest.setName(ORGANIZATION_NAME);
    organizationRequest.setCity(ORGANIZATION_CITY);
    organizationRequest.setAddress(ORGANIZATION_ADDRESS);
    organizationRequest.setPhone(ORGANIZATION_PHONE);
    organizationRequest.setEmail(ORGANIZATION_EMAIL);
    return organizationRequest;
  }
  public static OrganizationEntity getOrganizationEntity(){
    OrganizationEntity organizationEntity = new OrganizationEntity();
    organizationEntity.setId(ORGANIZATION_ID);
    organizationEntity.setName(ORGANIZATION_NAME);
    organizationEntity.setCity(ORGANIZATION_CITY);
    organizationEntity.setAddress(ORGANIZATION_ADDRESS);
    organizationEntity.setPhone(ORGANIZATION_PHONE);
    organizationEntity.setEmail(ORGANIZATION_EMAIL);
    return organizationEntity;
  }

}
