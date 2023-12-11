package com.softuni.eventem.exceptions;

public class TicketAlreadyExistsException extends RuntimeException{
  public TicketAlreadyExistsException(String message)
  {
    super(message);
  }

}
