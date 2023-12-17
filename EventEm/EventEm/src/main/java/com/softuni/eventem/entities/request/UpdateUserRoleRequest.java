package com.softuni.eventem.entities.request;

import com.softuni.eventem.entities.enums.UserRoleEnum;
import jakarta.validation.constraints.NotNull;

public class UpdateUserRoleRequest {

  @NotNull
  private UserRoleEnum role;

  public UpdateUserRoleRequest() {
  }

  public UserRoleEnum getRole() {
    return role;
  }

  public void setRole(UserRoleEnum role) {
    this.role = role;
  }
}
