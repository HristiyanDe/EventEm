package com.softuni.eventem.exceptions;

public class InvalidAuthenticationTokenException extends RuntimeException{
  public InvalidAuthenticationTokenException(String message){
    super(message);
  }

}
