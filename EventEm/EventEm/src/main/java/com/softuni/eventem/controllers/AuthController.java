package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.AuthenticationRequest;
import com.softuni.eventem.entities.request.RegisterRequest;
import com.softuni.eventem.entities.request.ResetPasswordRequest;
import com.softuni.eventem.entities.request.UpdateUserRoleByUsername;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.entities.request.UsernameRequest;
import com.softuni.eventem.entities.response.AuthenticationResponse;
import com.softuni.eventem.services.AuthenticationService;
import com.softuni.eventem.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  public final AuthenticationService authenticationService;
  private final UserService userService;

  public AuthController(AuthenticationService authenticationService, UserService userService) {
    this.authenticationService = authenticationService;
    this.userService = userService;
  }
@CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/register")
  ResponseEntity<AuthenticationResponse> register(
    @Validated @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(authenticationService.register(request));
  }
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/authenticate")
  ResponseEntity<AuthenticationResponse> authenticate(
    @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }
  @PostMapping("/reset-password")
  ResponseEntity<AuthenticationResponse> resetToken(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
    return ResponseEntity.ok(authenticationService.resetPassword(resetPasswordRequest));
  }
  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Void> updateUserRole(@PathVariable @NotNull UUID id, @RequestBody @Valid UpdateUserRoleRequest updateUserRoleRequest)
  {
    userService.updateUserRole(id, updateUserRoleRequest);
    return ResponseEntity.noContent().build();
  }
  @PatchMapping("/roles")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Void> updateUserRoles(@RequestBody UpdateUserRoleByUsername updateUserRoleByUsername){
    userService.updateUserRoles(updateUserRoleByUsername);
    return ResponseEntity.ok().build();
  }
  @GetMapping("/roles")
  public ResponseEntity<List<String>> getRoles() {
    return ResponseEntity.ok(userService.getUserRoles());
  }

}
