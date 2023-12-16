package com.softuni.eventem.configs;

import com.softuni.eventem.repositories.UserDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.USER_WITH_NAME_NOT_FOUND_ERROR_MESSAGE;

@Configuration
public class ApplicationBeanConfiguration {
  private final UserDetailsRepository userDetailsRepository;

  public ApplicationBeanConfiguration(UserDetailsRepository userDetailsRepository) {
    this.userDetailsRepository = userDetailsRepository;
  }

  @Bean
public ModelMapper getModelMapper(){return new ModelMapper();}
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userDetailsRepository.findByUsername(username)
                                     .orElseThrow(
                                       () -> new UsernameNotFoundException(USER_WITH_NAME_NOT_FOUND_ERROR_MESSAGE));
  }
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }
}
