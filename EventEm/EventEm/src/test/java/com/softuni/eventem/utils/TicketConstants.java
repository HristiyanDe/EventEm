package com.softuni.eventem.utils;

import java.math.BigDecimal;
import java.util.UUID;

import static com.softuni.eventem.utils.EventConstants.EVENT_ID;

public final class TicketConstants {
  public static final Long TICKET_ID = 10L;
  public static final String TICKET_NAME = "Test Ticket Name";
  public static final UUID TICKET_EVENT_ID = EVENT_ID;
  public static final BigDecimal TICKET_PRICE = BigDecimal.valueOf(100.00);
  public static final String DUPLICATE_MESSAGE = "Failed to create ticket as it is a duplicate";


}
