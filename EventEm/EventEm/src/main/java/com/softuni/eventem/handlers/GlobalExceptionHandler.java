package com.softuni.eventem.handlers;

import com.softuni.eventem.exceptions.VenueAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.CAUGHT_EXCEPTION_MESSAGE;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  private Map<String, List<String>> formatErrorsResponse(String... errors) {
    return formatErrorsResponse(Arrays.stream(errors).collect(Collectors.toList()));
  }

  private Map<String, List<String>> formatErrorsResponse(List<String> errors) {
    Map<String, List<String>> errorResponse = new HashMap<>(20);
    errorResponse.put("Errors", errors);
    return errorResponse;
  }


  @ExceptionHandler(VenueAlreadyExistsException.class)
  public ResponseEntity<Map<String, List<String>>> handleVenueAlreadyExistsException(VenueAlreadyExistsException exception)
  {
    log.error(CAUGHT_EXCEPTION_MESSAGE, exception);
    String error = exception.getMessage();
    Map<String, List<String>> errors = formatErrorsResponse(error);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

}
