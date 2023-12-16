package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.AuthenticationRequest;
import com.softuni.eventem.entities.request.RegisterRequest;
import com.softuni.eventem.entities.response.AuthenticationResponse;
import com.softuni.eventem.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  public final AuthenticationService authenticationService;

  public AuthController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/register")
  ResponseEntity<AuthenticationResponse> register(
    @Validated @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(authenticationService.register(request));
  }

  @PostMapping("/authenticate")
  ResponseEntity<AuthenticationResponse> authenticate(
    @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }
}
