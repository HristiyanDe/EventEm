package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.UserDetailsImpl;
import com.softuni.eventem.entities.dto.OrganizationDTO;
import com.softuni.eventem.entities.enums.UserRoleEnum;
import com.softuni.eventem.entities.request.OrganizationRequest;
import com.softuni.eventem.exceptions.OrganizationAlreadyExistsException;
import com.softuni.eventem.exceptions.OrganizationEntityNotFoundException;
import com.softuni.eventem.exceptions.UserUnauthorizedException;
import com.softuni.eventem.repositories.OrganizationRepository;
import com.softuni.eventem.services.OrganizationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.ENTITY_ALREADY_EXISTS_ERROR;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.ORGANIZATION_ALREADY_EXISTS_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.ORGANIZATION_CREATED_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.ORGANIZATION_NOT_FOUND_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.USER_LACKS_AUTHORITY_ERROR_MESSAGE;

@Service
public class OrganizationServiceImpl implements OrganizationService {

  public static final Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);

  private final ModelMapper modelMapper;
  private final OrganizationRepository organizationRepository;

  public OrganizationServiceImpl(ModelMapper modelMapper, OrganizationRepository organizationRepository) {
    this.modelMapper = modelMapper;
    this.organizationRepository = organizationRepository;
  }

  @Override
  public OrganizationEntity createOrganization(OrganizationRequest organizationRequest) {
    try {
      OrganizationEntity organization = organizationRepository.save(
        modelMapper.map(organizationRequest, OrganizationEntity.class)
      );
      logger.info(String.format(
        ORGANIZATION_CREATED_MESSAGE, organization));
      return organization;
    } catch (DataIntegrityViolationException e) {
      logger.error(String.format(ENTITY_ALREADY_EXISTS_ERROR, organizationRequest));
      throw new OrganizationAlreadyExistsException(ORGANIZATION_ALREADY_EXISTS_ERROR_MESSAGE);
    }
  }

  @Override
  public OrganizationEntity getOrganizationById(Long organizationId) {

    return organizationRepository.findById(organizationId).orElseThrow(() ->new OrganizationEntityNotFoundException(String.format(ORGANIZATION_NOT_FOUND_ERROR_MESSAGE, organizationId)));
  }

  @Override
  public List<OrganizationDTO> getOrganizationsByUserId(Long id) {
    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (!userDetails.getUser().getId().equals(id) || !userDetails.getRole().equals(UserRoleEnum.ADMIN))
    {
      throw new UserUnauthorizedException(String.format(USER_LACKS_AUTHORITY_ERROR_MESSAGE, userDetails.getUser().getId()));
    }
    return organizationRepository.findAllByUserId(id).stream().map(o -> modelMapper.map(o,OrganizationDTO.class)).toList();
  }
}
