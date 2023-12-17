package com.softuni.eventem.exceptions;

public class UserWithIdNotFoundException extends RuntimeException{
  public UserWithIdNotFoundException(String message){
    super(message);
  }

}
