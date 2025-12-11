package com.softuni.eventem.entities.request;

import com.softuni.eventem.entities.enums.UserRoleEnum;

public class UpdateUserRoleByUsername {
  private String username;
  private UserRoleEnum role;

  public UpdateUserRoleByUsername() {
  }

  public UpdateUserRoleByUsername(String username, UserRoleEnum role) {
    this.username = username;
    this.role = role;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserRoleEnum getRole() {
    return role;
  }

  public void setRole(UserRoleEnum role) {
    this.role = role;
  }
}
