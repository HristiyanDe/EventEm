package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.request.UserRequest;
import com.softuni.eventem.repositories.UserRepository;
import com.softuni.eventem.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
  private static final Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);
  private final ModelMapper modelMapper;
  private final UserRepository userRepository;

  public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
    this.modelMapper = modelMapper;
    this.userRepository = userRepository;
  }

  @Transactional
  @Override
  public UserEntity createUser(UserRequest userRequest) {

    return userRepository.save(modelMapper.map(userRequest, UserEntity.class));

  }
}
