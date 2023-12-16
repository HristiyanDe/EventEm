package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.enums.UserRoleEnum;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService, UserDetailsService userDetailsService) {
    this.userService = userService;
  }
  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Void> updateUserRole(@PathVariable @NotNull Long id, @RequestBody @Valid UpdateUserRoleRequest updateUserRoleRequest)
  {
    userService.updateUserRole(id, updateUserRoleRequest);
    return ResponseEntity.noContent().build();
  }
}
