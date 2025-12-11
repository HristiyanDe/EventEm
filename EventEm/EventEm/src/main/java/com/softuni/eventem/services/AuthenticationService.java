package com.softuni.eventem.services;

import com.softuni.eventem.entities.request.AuthenticationRequest;
import com.softuni.eventem.entities.request.RegisterRequest;
import com.softuni.eventem.entities.request.ResetPasswordRequest;
import com.softuni.eventem.entities.response.AuthenticationResponse;

public interface AuthenticationService {
  AuthenticationResponse register(RegisterRequest request);
  AuthenticationResponse authenticate(AuthenticationRequest request);
  AuthenticationResponse resetPassword(ResetPasswordRequest request);
}
