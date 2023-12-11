package com.softuni.eventem.exceptions;

public class VenueUnavailableBetweenDatesException extends RuntimeException{
  public VenueUnavailableBetweenDatesException(String message) {
    super(message);
  }
}
