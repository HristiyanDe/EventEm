package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.request.OrganizationRequest;
import com.softuni.eventem.exceptions.OrganizationAlreadyExistsException;
import com.softuni.eventem.repositories.OrganizationRepository;
import com.softuni.eventem.utils.OrganizationFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceImplTest {
  private OrganizationRequest organizationRequest;
  private OrganizationEntity organizationEntity;
  @Mock
  private OrganizationRepository organizationRepository;
  @Mock
  private ModelMapper modelMapper;
  @InjectMocks
  private OrganizationServiceImpl organizationService;
  @Before
  public void setup(){
    organizationRequest = OrganizationFactory.getOrganizationRequest();
    organizationEntity = OrganizationFactory.getOrganizationEntity();
  }

  @Test
  public void testCreateOrganization_success(){
    when(organizationRepository.save(any(OrganizationEntity.class))).thenReturn(organizationEntity);
    when(modelMapper.map(organizationRequest, OrganizationEntity.class)).thenReturn(organizationEntity);

    OrganizationEntity organization = organizationService.createOrganization(organizationRequest);

    verify(organizationRepository, times(1)).save(organizationEntity);
    verify(modelMapper, times(1)).map(organizationRequest, OrganizationEntity.class);

    assertEquals(10L,organization.getId());
  }

  @Test(expected = OrganizationAlreadyExistsException.class)
  public void testCreateOrganization_ThrowsOrganizationAlreadyExists()
  {
    when(organizationRepository.save(any(OrganizationEntity.class))).thenThrow(DataIntegrityViolationException.class);
    when(modelMapper.map(organizationRequest, OrganizationEntity.class)).thenReturn(organizationEntity);

    organizationService.createOrganization(organizationRequest);

    verify(organizationRepository, times(1)).save(organizationEntity);
    verify(modelMapper, times(1)).map(organizationRequest, OrganizationEntity.class);

  }
}
