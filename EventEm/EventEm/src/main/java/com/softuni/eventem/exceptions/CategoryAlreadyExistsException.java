package com.softuni.eventem.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException{

  public CategoryAlreadyExistsException(String message) {
    super(message);
  }
}
