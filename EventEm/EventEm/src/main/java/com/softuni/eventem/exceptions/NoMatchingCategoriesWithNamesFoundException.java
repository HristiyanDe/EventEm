package com.softuni.eventem.exceptions;

public class NoMatchingCategoriesWithNamesFoundException extends RuntimeException{
  public NoMatchingCategoriesWithNamesFoundException(String message) {
    super(message);
  }
}
