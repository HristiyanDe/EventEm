package com.softuni.eventem.handlers;

import com.softuni.eventem.exceptions.CategoryAlreadyExistsException;
import com.softuni.eventem.exceptions.EventAlreadyExistsException;
import com.softuni.eventem.exceptions.NoMatchingCategoriesWithNamesFoundException;
import com.softuni.eventem.exceptions.OrganizationAlreadyExistsException;
import com.softuni.eventem.exceptions.OrganizationEntityNotFoundException;
import com.softuni.eventem.exceptions.TicketAlreadyExistsException;
import com.softuni.eventem.exceptions.VenueAlreadyExistsException;
import com.softuni.eventem.exceptions.VenueEntityNotFoundException;
import com.softuni.eventem.exceptions.VenueUnavailableBetweenDatesException;
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

  @ExceptionHandler(OrganizationAlreadyExistsException.class)
  public ResponseEntity<Map<String, List<String>>> handleOrganizationAlreadyExistsException(OrganizationAlreadyExistsException exception)
  {
    log.error(CAUGHT_EXCEPTION_MESSAGE, exception);
    String error = exception.getMessage();
    Map<String, List<String>> errors = formatErrorsResponse(error);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(CategoryAlreadyExistsException.class)
  public ResponseEntity<Map<String, List<String>>> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException exception)
  {
    log.error(CAUGHT_EXCEPTION_MESSAGE, exception);
    String error = exception.getMessage();
    Map<String, List<String>> errors = formatErrorsResponse(error);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(NoMatchingCategoriesWithNamesFoundException.class)
  public ResponseEntity<Map<String, List<String>>> handleNoMatchingCategoriesFoundException(
    NoMatchingCategoriesWithNamesFoundException exception)
  {
    log.error(CAUGHT_EXCEPTION_MESSAGE, exception);
    String error = exception.getMessage();
    Map<String, List<String>> errors = formatErrorsResponse(error);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(VenueUnavailableBetweenDatesException.class)
  public ResponseEntity<Map<String, List<String>>> handleVenueUnavailableBetweenDatesException(
    VenueUnavailableBetweenDatesException exception)
  {
    log.error(CAUGHT_EXCEPTION_MESSAGE, exception);
    String error = exception.getMessage();
    Map<String, List<String>> errors = formatErrorsResponse(error);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(OrganizationEntityNotFoundException.class)
  public ResponseEntity<Map<String, List<String>>> handleOrganizationEntityNotFoundException(
    OrganizationEntityNotFoundException exception)
  {
    log.error(CAUGHT_EXCEPTION_MESSAGE, exception);
    String error = exception.getMessage();
    Map<String, List<String>> errors = formatErrorsResponse(error);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(VenueEntityNotFoundException.class)
  public ResponseEntity<Map<String, List<String>>> handleVenueEntityNotFoundException(
    VenueEntityNotFoundException exception)
  {
    log.error(CAUGHT_EXCEPTION_MESSAGE, exception);
    String error = exception.getMessage();
    Map<String, List<String>> errors = formatErrorsResponse(error);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(EventAlreadyExistsException.class)
  public ResponseEntity<Map<String, List<String>>> handleEventAlreadyExistsException(EventAlreadyExistsException exception)
  {
    log.error(CAUGHT_EXCEPTION_MESSAGE, exception);
    String error = exception.getMessage();
    Map<String, List<String>> errors = formatErrorsResponse(error);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(TicketAlreadyExistsException.class)
  public ResponseEntity<Map<String, List<String>>> handleTicketAlreadyExistsException(TicketAlreadyExistsException exception)
  {
    log.error(CAUGHT_EXCEPTION_MESSAGE, exception);
    String error = exception.getMessage();
    Map<String, List<String>> errors = formatErrorsResponse(error);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

}
