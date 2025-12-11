package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.CategoryEntity;
import com.softuni.eventem.entities.TicketEntity;
import com.softuni.eventem.entities.request.TicketRequest;
import com.softuni.eventem.exceptions.CategoryAlreadyExistsException;
import com.softuni.eventem.exceptions.TicketAlreadyExistsException;
import com.softuni.eventem.repositories.TicketRepository;
import com.softuni.eventem.services.TicketService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.CATEGORY_ALREADY_EXISTS_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.CATEGORY_CREATED_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.ENTITY_ALREADY_EXISTS_ERROR;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.TICKET_ALREADY_EXISTS_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.TICKET_CREATED_MESSAGE;

@Service
public class TicketServiceImpl implements TicketService {

  public static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
  private final TicketRepository ticketRepository;
  private final ModelMapper modelMapper;

  public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper) {
    this.ticketRepository = ticketRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public TicketEntity createTicket(TicketRequest ticketRequest) {
    try {
      TicketEntity ticket = ticketRepository.save(
        modelMapper.map(ticketRequest, TicketEntity.class)
      );
      logger.info(String.format(
        TICKET_CREATED_MESSAGE, ticket));
      return ticket;
    } catch (DataIntegrityViolationException e) {
      logger.error(String.format(ENTITY_ALREADY_EXISTS_ERROR, ticketRequest));
      throw new TicketAlreadyExistsException(TICKET_ALREADY_EXISTS_ERROR_MESSAGE);
    }
  }

  @Override
  public List<TicketEntity> getAllTicketEntitiesNamePriceAndIdByIds(List<UUID> tickedIds) {
    List<TicketEntity> ticketEntities = ticketRepository.findAllById(tickedIds);
    return ticketEntities;
  }
}
