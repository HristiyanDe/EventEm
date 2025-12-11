package com.softuni.eventem.constants;

public final class LoggerAndExceptionConstants {
  public static final String ENTITY_ALREADY_EXISTS_ERROR = "%s%n Is a duplicate";
  public static final String VENUE_CREATED_MESSAGE = "Venue %s created successfully";
  public static final String VENUE_ALREADY_EXISTS_ERROR_MESSAGE = "Failed to create venue as it is a duplicate";
  public static final String EVENT_ALREADY_EXISTS_ERROR_MESSAGE = "Failed to create event as it is a duplicate";
  public static final String EVENT_CREATED_MESSAGE = "Event %s created";
  public static final String CAUGHT_EXCEPTION_MESSAGE = "Caught exception:";
  public static final String ORGANIZATION_CREATED_MESSAGE = "Organization %s created successfully";
  public static final String ORGANIZATION_ALREADY_EXISTS_ERROR_MESSAGE = "Failed to create organization as it is a duplicate";
  public static final String CATEGORY_CREATED_MESSAGE = "Category %s created successfully";
  public static final String CATEGORY_ALREADY_EXISTS_ERROR_MESSAGE = "Failed to create category as it is a duplicate";
  public static final String NO_MATCHING_CATEGORIES_FOUND_ERROR_MESSAGE = "Failed to find categories with provided names";
  public static final String VENUE_UNAVAILABLE_BETWEEN_DATES_ERROR_MESSAGE = "Venue %s is unavailable between dates %s and %s";
  public static final String ORGANIZATION_NOT_FOUND_ERROR_MESSAGE = "Organization with id %d not found";
  public static final String TICKET_CREATED_MESSAGE = "Ticket %s created successfully";
  public static final String TICKET_ALREADY_EXISTS_ERROR_MESSAGE = "Failed to create ticket as it is a duplicate";
  public static final String INVALID_JWT_TOKEN_ERROR = "Invalid token!";
  public static String AUTHENTICATE_USER_MESSAGE = "Authenticating user with username: %s.";
  public static String RESET_USER_PASSWORD_MESSAGE = "Reset password for user: %s.";

  public static String AUTHENTICATE_SUCCESS_MESSAGE = "Authentication has been successful for username: %s.";
  public static String REGISTERING_USER_MESSAGE = "Registering user with username: %s.";
  public static String REGISTERING_USER_SUCCESS_MESSAGE = "Successfully registered user with username: %s.";
  public static String DATA_INTEGRITY_VIOLATION_ERROR_MESSAGE = "Encountered DataIntegrityViolation when attempting to save to database: %s";
  public static final String WRONG_CREDENTIALS_ERROR_MESSAGE = "Incorrect credentials";
  public static final String USER_WITH_NAME_NOT_FOUND_ERROR_MESSAGE = "User not found with entered username";
  public static final String USER_WITH_ID_NOT_FOUND_ERROR_MESSAGE = "User with id %d not found";
  public static final String UPDATING_USER_ROLES_MESSAGE = "Updating user %d's role to %s";
  public static final String UPDATING_USER_USERNAME_MESSAGE = "Updating user %d's username to %s";
  public static final String USER_LACKS_AUTHORITY_ERROR_MESSAGE = "Current user %d lacks authority for this action";
  public static final String UPDATING_USER_PROFILE_MESSAGE = "Updating user %d's profile";
  public static final String RETRIEVING_CATEGORIES_MESSAGE = "Retrieving unfiltered categories";
}
