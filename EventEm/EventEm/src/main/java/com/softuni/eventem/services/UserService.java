package com.softuni.eventem.services;

import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.request.UpdateUserRoleByUsername;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.entities.request.UpdateUserSecurityInfoRequest;
import com.softuni.eventem.entities.request.UserRequest;
import com.softuni.eventem.entities.request.UsernameRequest;
import com.softuni.eventem.repositories.projection.AdminUserListDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

  UserEntity createUser(UserRequest userRequest);

  void updateUserRole(UUID id, UpdateUserRoleRequest updateUserRoleRequest);

  String updateUserSecurityDetails(UUID id, UpdateUserSecurityInfoRequest updateUserSecurityInfoRequest);

  UserEntity updateUserProfile(UUID id, UserRequest userRequest);

  List<AdminUserListDTO> findUsersByUsername(String username);

  boolean banUserByUsername(UsernameRequest username);

  boolean updateUserRoles(UpdateUserRoleByUsername updateUserRoleByUsername);

  List<String> getUserRoles();
}
