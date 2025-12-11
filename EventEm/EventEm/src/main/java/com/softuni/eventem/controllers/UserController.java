package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.dto.OrganizationDTO;
import com.softuni.eventem.entities.request.UpdateUserSecurityInfoRequest;
import com.softuni.eventem.entities.request.UpdateUserUsernameRequest;
import com.softuni.eventem.entities.request.UserRequest;
import com.softuni.eventem.entities.request.UsernameRequest;
import com.softuni.eventem.repositories.projection.AdminUserListDTO;
import com.softuni.eventem.services.OrganizationService;
import com.softuni.eventem.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User Controller", description = "The User API. Contains all the operations that can be performed on a user")
public class UserController {
  private final UserService userService;
  private final OrganizationService organizationService;

  public UserController(UserService userService, UserDetailsService userDetailsService,
                        OrganizationService organizationService) {
    this.userService = userService;
    this.organizationService = organizationService;
  }
  @CrossOrigin(origins = "http://localhost:3000")
  @PutMapping("/{id}/security")
  public ResponseEntity<String> updateUserSecurityDetails(@PathVariable @NotNull UUID id, @RequestBody @Valid UpdateUserSecurityInfoRequest updateUserSecurityInfoRequest)
  {
    return ResponseEntity.ok(userService.updateUserSecurityDetails(id,updateUserSecurityInfoRequest));
  }
  @CrossOrigin(origins = "http://localhost:3000")
  @PutMapping("/{id}")
  public ResponseEntity<UserEntity> updateUserProfile(@PathVariable @NotNull UUID id, @RequestBody @Valid UserRequest userRequest)
  {

    return ResponseEntity.ok(userService.updateUserProfile(id,userRequest));
  }
  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/{id}/organizations")
  public ResponseEntity<List<OrganizationDTO>> getOrganizationsByUser(@PathVariable @NotNull UUID id)
  {
  return ResponseEntity.ok(organizationService.getOrganizationsByUserId(id));
  }
  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/search")
  public ResponseEntity<List<AdminUserListDTO>> findUsersByUsername(@RequestParam(value = "username") String username){
    return ResponseEntity.ok(userService.findUsersByUsername(username));
  }
  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping("/ban")
  public ResponseEntity<Boolean> banUserByUsername(@RequestBody UsernameRequest username){
    return ResponseEntity.ok(userService.banUserByUsername(username));
  }

}
