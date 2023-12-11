package com.softuni.eventem.utils;

import com.softuni.eventem.entities.TicketEntity;
import com.softuni.eventem.entities.request.TicketRequest;

import static com.softuni.eventem.utils.TicketConstants.TICKET_EVENT_ID;
import static com.softuni.eventem.utils.TicketConstants.TICKET_ID;
import static com.softuni.eventem.utils.TicketConstants.TICKET_NAME;
import static com.softuni.eventem.utils.TicketConstants.TICKET_PRICE;

public final class TicketFactory {
  public static TicketRequest getTicketRequest(){
    TicketRequest ticketRequest = new TicketRequest();
    ticketRequest.setName(TICKET_NAME);
    ticketRequest.setEventId(TICKET_EVENT_ID);
    ticketRequest.setPrice(TICKET_PRICE);
    return ticketRequest;
  }
  public static TicketEntity getTicketEntity(){
    TicketEntity ticketEntity = new TicketEntity();
    ticketEntity.setId(TICKET_ID);
    ticketEntity.setName(TICKET_NAME);
    ticketEntity.setEventId(TICKET_EVENT_ID);
    ticketEntity.setPrice(TICKET_PRICE);
    return ticketEntity;
  }

}
