package com.softuni.eventem.exceptions;

public class UserUnauthorizedException extends RuntimeException{
  public UserUnauthorizedException(String message)
  {
    super(message);
  }

}
