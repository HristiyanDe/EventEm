package com.softuni.eventem.services;

import com.softuni.eventem.entities.TicketEntity;
import com.softuni.eventem.entities.request.TicketRequest;

import java.util.List;
import java.util.UUID;

public interface TicketService {
  TicketEntity createTicket(TicketRequest ticketRequest);

  List<TicketEntity> getAllTicketEntitiesNamePriceAndIdByIds(List<UUID> tickedIds);
}
