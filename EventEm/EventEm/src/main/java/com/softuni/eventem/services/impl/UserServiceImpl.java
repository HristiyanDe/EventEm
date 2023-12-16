package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.UserDetailsImpl;
import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.entities.request.UserRequest;
import com.softuni.eventem.exceptions.UserWithIdNotFoundException;
import com.softuni.eventem.repositories.UserDetailsRepository;
import com.softuni.eventem.repositories.UserRepository;
import com.softuni.eventem.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.UPDATING_USER_ROLES_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.USER_WITH_ID_NOT_FOUND_ERROR_MESSAGE;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);
  private final ModelMapper modelMapper;
  private final UserRepository userRepository;
  private final UserDetailsRepository userDetailsRepository;

  public UserServiceImpl(
    ModelMapper modelMapper, UserRepository userRepository,
    UserDetailsRepository userDetailsRepository) {
    this.modelMapper = modelMapper;
    this.userRepository = userRepository;
    this.userDetailsRepository = userDetailsRepository;
  }

  @Transactional
  @Override
  public UserEntity createUser(UserRequest userRequest) {

    return userRepository.save(modelMapper.map(userRequest, UserEntity.class));
  }
//TODO: Admins shouldn't be able to un-admin other admins
  @Transactional
  @Override
  public void updateUserRole(Long id, UpdateUserRoleRequest updateUserRoleRequest) {
    logger.info(String.format(UPDATING_USER_ROLES_MESSAGE, id, updateUserRoleRequest.getRole().name()));
    if (userDetailsRepository.updateUserRole(id, updateUserRoleRequest) != 1) {
      throw new UserWithIdNotFoundException(String.format(USER_WITH_ID_NOT_FOUND_ERROR_MESSAGE, id));
    }
  }
}
