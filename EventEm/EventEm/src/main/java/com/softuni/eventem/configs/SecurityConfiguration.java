package com.softuni.eventem.configs;

import com.softuni.eventem.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

//TODO: Unit testing!!!!!
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
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
      .cors(cors -> cors.configurationSource(corsConfigurationSource()))
      .csrf(AbstractHttpConfigurer::disable)

      .authorizeHttpRequests(
        authorize -> authorize
          .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
          .requestMatchers("/api/auth/register", "/api/auth/authenticate", "/api/auth/reset-password").anonymous()
          .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
          .requestMatchers(HttpMethod.GET, "/api/venues/**").permitAll()
          .requestMatchers(HttpMethod.GET, "/api/venues").permitAll()
          .requestMatchers(HttpMethod.GET, "/api/users/*").permitAll()
          .requestMatchers(HttpMethod.GET, "/api/events/*").permitAll()
          .requestMatchers(HttpMethod.POST, "/api/events/*").hasAuthority("ADMIN")
          .anyRequest()
          .authenticated())
      .sessionManagement(httpSecuritySessionManagementConfigurer
                           -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
        SessionCreationPolicy.STATELESS))
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3001"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    configuration.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}

