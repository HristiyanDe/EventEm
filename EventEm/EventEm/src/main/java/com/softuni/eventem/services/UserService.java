package com.softuni.eventem.services;

import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.entities.request.UpdateUserUsernameRequest;
import com.softuni.eventem.entities.request.UserRequest;

public interface UserService {

  UserEntity createUser(UserRequest userRequest);

  void updateUserRole(Long id, UpdateUserRoleRequest updateUserRoleRequest);

  void updateUserUsername(Long id, UpdateUserUsernameRequest updateUserUsernameRequest);

  void updateUserProfile(Long id, UserRequest userRequest);
}
