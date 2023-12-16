package com.softuni.eventem.configs;

import com.softuni.eventem.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//TODO: Unit testing!!!!!
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
  private final JwtAuthenticationFilter authFilter;
  private final AuthenticationProvider authenticationProvider;

  public SecurityConfiguration(JwtAuthenticationFilter authFilter, AuthenticationProvider authenticationProvider) {
    this.authFilter = authFilter;
    this.authenticationProvider = authenticationProvider;
  }
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(
        authorize -> authorize
          .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
          .permitAll()
          .requestMatchers("/api/auth/register", "/api/auth/authenticate")
          .anonymous()
          .anyRequest()
          .authenticated())
      .sessionManagement(httpSecuritySessionManagementConfigurer
                           -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
        SessionCreationPolicy.STATELESS))
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
