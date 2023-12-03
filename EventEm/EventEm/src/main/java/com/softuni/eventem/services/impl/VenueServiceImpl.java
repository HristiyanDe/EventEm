package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.request.VenueRequest;
import com.softuni.eventem.repositories.VenueRepository;
import com.softuni.eventem.services.VenueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl implements VenueService {

  private final VenueRepository venueRepository;
  private final ModelMapper modelMapper;

  public VenueServiceImpl(VenueRepository venueRepository, ModelMapper modelMapper) {
    this.venueRepository = venueRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public VenueEntity createVenue(VenueRequest venueRequest) {
    return venueRepository.save(
      modelMapper.map(venueRequest, VenueEntity.class)
    );
  }
}
