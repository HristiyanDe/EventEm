package com.softuni.eventem.entities.enums;

public enum UserRoleEnum {
  ADMIN("ADMIN"),
  USER("USER");
  private final String value;

  UserRoleEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
