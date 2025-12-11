package com.softuni.eventem.entities.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ResetPasswordRequest {
  @Pattern(regexp = "^[A-Za-z0-9_-]{6,}$", message = "Username must be at least 6 characters")
  private String username;
  @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z0-9]{6,}$",
           message = "Password should be at least 8 characters, containing one Uppercase letter and a digit")
  private String password;
  @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z0-9]{6,}$",
           message = "Password should be at least 8 characters, containing one Uppercase letter and a digit")
  private String confirmPassword;

  public ResetPasswordRequest() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
  @AssertTrue(message = "Passwords don't match")
  public boolean isPasswordMismatched() {
    return password.equals(confirmPassword);
  }
}
