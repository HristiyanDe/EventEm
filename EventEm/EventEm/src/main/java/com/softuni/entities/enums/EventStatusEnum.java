package com.softuni.entities.enums;

public enum EventStatusEnum {
  UPCOMING("Upcoming"),
  DELAYED("Delayed"),
  CANCELLED("Cancelled"),
  IN_PROGRESS("In progress"),
  FINISHED("Finished"),
  LOCKED("Locked");
  private final String value;

  EventStatusEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
