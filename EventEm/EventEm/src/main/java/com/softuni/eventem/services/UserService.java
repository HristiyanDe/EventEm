package com.softuni.eventem.services;

import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.request.UserRequest;

public interface UserService {

  UserEntity createUser(UserRequest userRequest);
}
