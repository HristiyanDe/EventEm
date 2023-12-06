package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.request.VenueRequest;
import com.softuni.eventem.exceptions.VenueAlreadyExistsException;
import com.softuni.eventem.repositories.VenueRepository;
import com.softuni.eventem.utils.VenueFactory;
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
public class VenueServiceImplTest {
  private VenueRequest venueRequest;
  private VenueEntity venueEntity;
  @Mock
  private VenueRepository venueRepository;
  @Mock
  private ModelMapper modelMapper;
  @InjectMocks
  private VenueServiceImpl venueService;

  @Before
  public void setup(){
    venueRequest = VenueFactory.getVenueRequest();
    venueEntity = VenueFactory.getVenueEntity();
  }
  @Test
  public void testCreateVenue_success(){
    when(venueRepository.save(any(VenueEntity.class))).thenReturn(venueEntity);
    when(modelMapper.map(venueRequest, VenueEntity.class)).thenReturn(venueEntity);

    VenueEntity venue = venueService.createVenue(venueRequest);

    verify(venueRepository, times(1)).save(venueEntity);
    verify(modelMapper, times(1)).map(venueRequest, VenueEntity.class);

    assertEquals(10L,venue.getId());
  }

  @Test(expected = VenueAlreadyExistsException.class)
  public void testCreateVenue_ThrowsVenueAlreadyExists()
  {
    when(venueRepository.save(any(VenueEntity.class))).thenThrow(DataIntegrityViolationException.class);
    when(modelMapper.map(venueRequest, VenueEntity.class)).thenReturn(venueEntity);

    venueService.createVenue(venueRequest);

    verify(venueRepository, times(1)).save(venueEntity);
    verify(modelMapper, times(1)).map(venueRequest, VenueEntity.class);

  }

}
