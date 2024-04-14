package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.UserDetailsImpl;
import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.enums.UserRoleEnum;
import com.softuni.eventem.entities.request.AuthenticationRequest;
import com.softuni.eventem.entities.request.RegisterRequest;
import com.softuni.eventem.entities.response.AuthenticationResponse;
import com.softuni.eventem.exceptions.WrongCredentialsException;
import com.softuni.eventem.jwt.JwtTokenUtil;
import com.softuni.eventem.repositories.UserDetailsRepository;
import com.softuni.eventem.services.AuthenticationService;
import com.softuni.eventem.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.AUTHENTICATE_SUCCESS_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.AUTHENTICATE_USER_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.REGISTERING_USER_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.REGISTERING_USER_SUCCESS_MESSAGE;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
  private final UserDetailsRepository userDetailsRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenUtil jwtTokenUtil;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final ModelMapper modelMapper;

  public AuthenticationServiceImpl(
    UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder,
    JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager,
    UserService userService, ModelMapper modelMapper) {
    this.userDetailsRepository = userDetailsRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenUtil = jwtTokenUtil;
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    log.info(String.format(AUTHENTICATE_USER_MESSAGE, request.getUsername()));
    UserDetailsImpl userDetails =
      userDetailsRepository.findByUsername(request.getUsername()).orElseThrow(WrongCredentialsException::new);
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          request.getUsername(),
          request.getPassword()
        )
      );
    } catch (AuthenticationException e) {
      throw new WrongCredentialsException();
    }
    log.info(String.format(AUTHENTICATE_SUCCESS_MESSAGE, request.getUsername()));
    return new AuthenticationResponse(jwtTokenUtil.generateToken(userDetails), userDetails.getUser().getId());
  }

  @Transactional
  @Override
  public AuthenticationResponse register(RegisterRequest request) {
    log.info(String.format(REGISTERING_USER_MESSAGE, request.getUsername()));
    UserDetailsImpl userDetails;
    UserEntity user = userService.createUser(request.getUserRequest());

    userDetails = new UserDetailsImpl(
      request.getUsername(),
      passwordEncoder.encode(request.getPassword()), UserRoleEnum.USER,
      modelMapper.map(user, UserEntity.class));
    userDetails.setUser(user);
    userDetailsRepository.save(userDetails);
    log.info(String.format(REGISTERING_USER_SUCCESS_MESSAGE, userDetails.getUsername()));
    return new AuthenticationResponse(jwtTokenUtil.generateToken(userDetails), userDetails.getUser().getId());
  }
}
