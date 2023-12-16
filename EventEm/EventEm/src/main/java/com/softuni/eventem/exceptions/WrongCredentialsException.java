package com.softuni.eventem.exceptions;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.WRONG_CREDENTIALS_ERROR_MESSAGE;

public class WrongCredentialsException extends RuntimeException{

  public WrongCredentialsException()
  {
    super(WRONG_CREDENTIALS_ERROR_MESSAGE);
  }


}
