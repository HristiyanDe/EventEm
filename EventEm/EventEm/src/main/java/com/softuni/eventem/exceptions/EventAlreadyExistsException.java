package com.softuni.eventem.exceptions;

public class EventAlreadyExistsException extends RuntimeException{
  public EventAlreadyExistsException(String message){
    super(message);
  }

}
