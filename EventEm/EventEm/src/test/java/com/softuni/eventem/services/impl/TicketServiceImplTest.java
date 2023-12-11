package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.TicketEntity;
import com.softuni.eventem.entities.request.TicketRequest;
import com.softuni.eventem.exceptions.TicketAlreadyExistsException;
import com.softuni.eventem.repositories.TicketRepository;
import com.softuni.eventem.utils.TicketFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.TICKET_ALREADY_EXISTS_ERROR_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

  @Mock
  private TicketRepository ticketRepository;
  @Mock
  private ModelMapper modelMapper;
  @InjectMocks
  private TicketServiceImpl ticketService;
  private TicketRequest ticketRequest;
  private TicketEntity ticketEntity;

  @Before
  public void setup() {
    ticketRequest = TicketFactory.getTicketRequest();
    ticketEntity = TicketFactory.getTicketEntity();
  }

  @Test
  public void testCreateTicket_success() {
    when(modelMapper.map(ticketRequest, TicketEntity.class)).thenReturn(ticketEntity);
    when(ticketRepository.save(any(TicketEntity.class))).thenReturn(ticketEntity);

    TicketEntity ticket = ticketService.createTicket(ticketRequest);

    verify(ticketRepository, times(1)).save(ticketEntity);
    verify(modelMapper, times(1)).map(ticketRequest, TicketEntity.class);
  }

  @Test
  public void testCreateTicket_ThrowsTicketAlreadyExists() {
    when(modelMapper.map(ticketRequest, TicketEntity.class)).thenReturn(ticketEntity);
    when(ticketRepository.save(any(TicketEntity.class))).thenThrow(DataIntegrityViolationException.class);

    TicketAlreadyExistsException exception =
      assertThrows(TicketAlreadyExistsException.class, () -> ticketService.createTicket(ticketRequest));

    verify(ticketRepository, times(1)).save(ticketEntity);
    verify(modelMapper, times(1)).map(ticketRequest, TicketEntity.class);
    assertEquals(TICKET_ALREADY_EXISTS_ERROR_MESSAGE,exception.getMessage());
  }
}
