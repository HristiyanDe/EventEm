package com.softuni.eventem.entities.response;

public class AuthenticationResponse {
  private String token;
  private Long id;

  public AuthenticationResponse() {
  }

  public AuthenticationResponse(String token, Long id) {
    this.token = token;
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
