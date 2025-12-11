package com.softuni.eventem.services;

import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.entities.request.UpdateUserSecurityInfoRequest;
import com.softuni.eventem.entities.request.UpdateUserUsernameRequest;
import com.softuni.eventem.entities.request.UserRequest;
import com.softuni.eventem.repositories.projection.AdminUserListDTO;

import java.util.List;

public interface UserService {

  UserEntity createUser(UserRequest userRequest);

  void updateUserRole(Long id, UpdateUserRoleRequest updateUserRoleRequest);

  String updateUserSecurityDetails(Long id, UpdateUserSecurityInfoRequest updateUserSecurityInfoRequest);

  UserEntity updateUserProfile(Long id, UserRequest userRequest);

  List<AdminUserListDTO> findUsersByUsername(String username);
}
