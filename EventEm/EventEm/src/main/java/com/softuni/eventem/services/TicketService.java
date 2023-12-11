package com.softuni.eventem.services;

import com.softuni.eventem.entities.TicketEntity;
import com.softuni.eventem.entities.request.TicketRequest;

public interface TicketService {
  TicketEntity createTicket(TicketRequest ticketRequest);

}
