package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.UserDetailsImpl;
import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.enums.UserRoleEnum;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.entities.request.UpdateUserUsernameRequest;
import com.softuni.eventem.entities.request.UserRequest;
import com.softuni.eventem.exceptions.UserUnauthorizedException;
import com.softuni.eventem.exceptions.UserWithIdNotFoundException;
import com.softuni.eventem.repositories.UserDetailsRepository;
import com.softuni.eventem.repositories.UserRepository;
import com.softuni.eventem.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.UPDATING_USER_PROFILE_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.UPDATING_USER_ROLES_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.UPDATING_USER_USERNAME_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.USER_LACKS_AUTHORITY_ERROR_MESSAGE;
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

  @Transactional
  @Override
  public void updateUserUsername(Long id, UpdateUserUsernameRequest updateUserUsernameRequest) {
  UserDetailsImpl user =(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  logger.info(String.format(UPDATING_USER_USERNAME_MESSAGE,id,updateUserUsernameRequest.getUsername()));
  if (user.getRole() != UserRoleEnum.ADMIN && !Objects.equals(user.getUser().getId(), id)){
    throw new UserUnauthorizedException(String.format(USER_LACKS_AUTHORITY_ERROR_MESSAGE,user.getUser().getId()));
  }
  if (userDetailsRepository.updateUserUsername(id,updateUserUsernameRequest)!=1)
  {
    throw new UserWithIdNotFoundException(String.format(USER_WITH_ID_NOT_FOUND_ERROR_MESSAGE, id));
  }
  }
  @Transactional
  @Override
  public void updateUserProfile(Long id, UserRequest userRequest) {
    UserDetailsImpl user =(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    logger.info(String.format(UPDATING_USER_PROFILE_MESSAGE,id));
    if (!Objects.equals(user.getUser().getId(), id))
    {
      throw new UserUnauthorizedException(String.format(USER_LACKS_AUTHORITY_ERROR_MESSAGE,user.getUser().getId()));
    }
    userRepository.updateUserProfile(id,userRequest);
  }
}
