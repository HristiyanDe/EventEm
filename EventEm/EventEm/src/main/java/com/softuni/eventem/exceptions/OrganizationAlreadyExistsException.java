package com.softuni.eventem.exceptions;

public class OrganizationAlreadyExistsException extends RuntimeException{

  public OrganizationAlreadyExistsException(String message) {
    super(message);
  }
}
