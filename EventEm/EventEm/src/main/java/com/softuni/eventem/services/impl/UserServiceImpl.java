package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.UserDetailsImpl;
import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.enums.UserRoleEnum;
import com.softuni.eventem.entities.request.UpdateUserRoleByUsername;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.entities.request.UpdateUserSecurityInfoRequest;
import com.softuni.eventem.entities.request.UserRequest;
import com.softuni.eventem.entities.request.UsernameRequest;
import com.softuni.eventem.exceptions.UserUnauthorizedException;
import com.softuni.eventem.exceptions.UserWithIdNotFoundException;
import com.softuni.eventem.exceptions.WrongCredentialsException;
import com.softuni.eventem.jwt.JwtTokenUtil;
import com.softuni.eventem.repositories.UserDetailsRepository;
import com.softuni.eventem.repositories.UserRepository;
import com.softuni.eventem.repositories.projection.AdminUserListDTO;
import com.softuni.eventem.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.UPDATING_USER_PROFILE_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.UPDATING_USER_ROLES_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.UPDATING_USER_USERNAME_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.USER_LACKS_AUTHORITY_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.USER_WITH_ID_NOT_FOUND_ERROR_MESSAGE;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);
  private final ModelMapper modelMapper;
  private final JwtTokenUtil jwtTokenUtil;
  private final UserRepository userRepository;
  private final UserDetailsRepository userDetailsRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;

  public UserServiceImpl(
    ModelMapper modelMapper, JwtTokenUtil jwtTokenUtil, UserRepository userRepository,
    UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
    this.modelMapper = modelMapper;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userRepository = userRepository;
    this.userDetailsRepository = userDetailsRepository;
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
  }

  @Transactional
  @Override
  public UserEntity createUser(UserRequest userRequest) {

    return userRepository.save(modelMapper.map(userRequest, UserEntity.class));
  }

  @Transactional
  @Override
  public void updateUserRole(UUID id, UpdateUserRoleRequest updateUserRoleRequest) {
    logger.info(String.format(UPDATING_USER_ROLES_MESSAGE, id, updateUserRoleRequest.getRole().name()));
    if (userDetailsRepository.updateUserRole(id, updateUserRoleRequest) != 1) {
      throw new UserWithIdNotFoundException(String.format(USER_WITH_ID_NOT_FOUND_ERROR_MESSAGE, id));
    }
  }
  @Transactional
  @Override
  public String updateUserSecurityDetails(UUID id, UpdateUserSecurityInfoRequest updateUserSecurityInfoRequest) {
  UserDetailsImpl user =(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  logger.info(String.format(UPDATING_USER_USERNAME_MESSAGE,id,updateUserSecurityInfoRequest.getUsername()));
  if (user.getRole() != UserRoleEnum.ADMIN && !Objects.equals(user.getUser().getId(), id)){
    throw new UserUnauthorizedException(String.format(USER_LACKS_AUTHORITY_ERROR_MESSAGE,user.getUser().getId()));
  }
  updateUserSecurityInfoRequest.setNewPassword(passwordEncoder.encode(updateUserSecurityInfoRequest.getPassword()));
    System.out.println(updateUserSecurityInfoRequest.getNewPassword());
  if (userDetailsRepository.updateUserDetails(id, updateUserSecurityInfoRequest) != 1)
  {
    throw new UserWithIdNotFoundException(String.format(USER_WITH_ID_NOT_FOUND_ERROR_MESSAGE, id));
  }
  if (!updateUserSecurityInfoRequest.getUsername().equals(user.getUsername())) {
    return jwtTokenUtil.generateToken(
      userDetailsRepository.findByUsername(updateUserSecurityInfoRequest.getUsername()).orElseThrow(
        WrongCredentialsException::new));
  }
  return jwtTokenUtil.generateToken(userDetailsRepository.findByUserId(id).orElseThrow(WrongCredentialsException::new));
  }
  @Transactional
  @Override
  public UserEntity updateUserProfile(UUID id, UserRequest userRequest) {
    UserDetailsImpl user =(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    logger.info(String.format(UPDATING_USER_PROFILE_MESSAGE,id));
    if (!Objects.equals(user.getUser().getId(), id))
    {
      throw new UserUnauthorizedException(String.format(USER_LACKS_AUTHORITY_ERROR_MESSAGE,user.getUser().getId()));
    }
    if (userRepository.updateUserProfile(id,userRequest)!=1)
    {
      throw new RuntimeException("Failed to update user");
    }
    return userRepository.findById(id).orElseThrow();
  }

  @Override
  public List<AdminUserListDTO> findUsersByUsername(String username) {
    UserDetailsImpl user =(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!user.getRole().equals(UserRoleEnum.ADMIN)) {
      throw new UserUnauthorizedException(String.format(USER_LACKS_AUTHORITY_ERROR_MESSAGE,user.getUserId()));
    }
    return userDetailsRepository.findByUsernameContaining(username).stream().toList();
  }

  @Override
  @Transactional
  public boolean banUserByUsername(UsernameRequest usernameRequest) {
    UserDetailsImpl user =(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!user.getRole().equals(UserRoleEnum.ADMIN)) {
      throw new UserUnauthorizedException(String.format(USER_LACKS_AUTHORITY_ERROR_MESSAGE,user.getUserId()));
    }
    return userDetailsRepository.updateUserEnabled(usernameRequest.getUsername()) > 0;
  }

  @Override
  @Transactional
  public boolean updateUserRoles(UpdateUserRoleByUsername updateUserRoleByUsername) {
    UserDetailsImpl user =(UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (!user.getRole().equals(UserRoleEnum.ADMIN)) {
      throw new UserUnauthorizedException(String.format(USER_LACKS_AUTHORITY_ERROR_MESSAGE,user.getUserId()));
    }
    return userDetailsRepository.updateUserRole(
      updateUserRoleByUsername.getUsername(),
      updateUserRoleByUsername.getRole()
    ) > 0;
  }

  @Override
  public List<String> getUserRoles() {
    return Arrays.stream(UserRoleEnum.values()).map(UserRoleEnum::name).collect(Collectors.toList());
  }
}
