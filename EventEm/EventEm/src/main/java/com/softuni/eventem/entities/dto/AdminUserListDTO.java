package com.softuni.eventem.entities.dto;

public class AdminUserListDTO {
  private String username;
  private String user_role;
  private String status;

  public AdminUserListDTO(String username, String role, String status) {
    this.username = username;
    this.user_role = role;
    this.status = status;
  }

  public AdminUserListDTO() {
  }
  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }

  public String getRole() {
    return user_role;
  }

  public void setRole(String role) {
    this.user_role = role;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
