package com.softuni.eventem.exceptions;

public class NoMatchingCategoriesFoundException extends RuntimeException{
  public NoMatchingCategoriesFoundException(String message) {
    super(message);
  }
}
