package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.UpdateUserUsernameRequest;
import com.softuni.eventem.entities.request.UserRequest;
import com.softuni.eventem.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User Controller", description = "The User API. Contains all the operations that can be performed on a user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService, UserDetailsService userDetailsService) {
    this.userService = userService;
  }
  @PatchMapping("/{id}")
  public ResponseEntity<Void> updateUserUsername(@PathVariable @NotNull Long id, @RequestBody @Valid UpdateUserUsernameRequest updateUserUsernameRequest)
  {
    userService.updateUserUsername(id,updateUserUsernameRequest);
    return ResponseEntity.noContent().build();
  }
  @PutMapping("/{id}")
  public ResponseEntity<Void> updateUserProfile(@PathVariable @NotNull Long id, @RequestBody @Valid UserRequest userRequest)
  {
    userService.updateUserProfile(id, userRequest);
    return ResponseEntity.noContent().build();
  }

}
