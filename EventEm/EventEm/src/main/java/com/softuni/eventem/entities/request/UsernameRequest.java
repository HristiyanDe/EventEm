package com.softuni.eventem.entities.request;

public class UsernameRequest {
  private String username;
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public UsernameRequest(String username) {
    this.username = username;
  }

  public UsernameRequest() {
  }
}
