package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.dto.VenueDTO;
import com.softuni.eventem.entities.request.VenueRequest;
import com.softuni.eventem.exceptions.VenueAlreadyExistsException;
import com.softuni.eventem.exceptions.VenueEntityNotFoundException;
import com.softuni.eventem.repositories.VenueRepository;
import com.softuni.eventem.services.VenueService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.ENTITY_ALREADY_EXISTS_ERROR;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.VENUE_ALREADY_EXISTS_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.VENUE_CREATED_MESSAGE;

@Service
public class VenueServiceImpl implements VenueService {

  public static final Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);

  private final VenueRepository venueRepository;
  private final ModelMapper modelMapper;

  public VenueServiceImpl(VenueRepository venueRepository, ModelMapper modelMapper) {
    this.venueRepository = venueRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public VenueEntity createVenue(VenueRequest venueRequest) {

    try {
      VenueEntity venue = venueRepository.save(
        modelMapper.map(venueRequest, VenueEntity.class)
      );
      logger.info(String.format(
        VENUE_CREATED_MESSAGE, venue));
      return venue;
    } catch (DataIntegrityViolationException e) {
      logger.error(String.format(ENTITY_ALREADY_EXISTS_ERROR, venueRequest));
      throw new VenueAlreadyExistsException(VENUE_ALREADY_EXISTS_ERROR_MESSAGE);
    }
  }

  @Override
  public VenueEntity getVenueById(UUID id) {
    return venueRepository.findById(id).orElseThrow(VenueEntityNotFoundException::new);
  }

  @Override
  public List<VenueDTO> getFilteredVenues(String venueName, String venueAddress, String venueCity) {
    return venueRepository.findAllVenuesFiltered(venueName,venueAddress,venueCity).stream().map(v -> modelMapper.map(v, VenueDTO.class)).toList();
  }

  @Override
  public List<VenueDTO> getAllVenues() {
    return venueRepository.findAll().stream().map(v -> modelMapper.map(v, VenueDTO.class)).toList();
  }
}
