package com.softuni.eventem.services;

import com.softuni.eventem.entities.TicketEntity;
import com.softuni.eventem.entities.request.TicketRequest;

import java.util.List;

public interface TicketService {
  TicketEntity createTicket(TicketRequest ticketRequest);

  List<TicketEntity> getAllTicketEntitiesNamePriceAndIdByIds(List<Long> tickedIds);
}
