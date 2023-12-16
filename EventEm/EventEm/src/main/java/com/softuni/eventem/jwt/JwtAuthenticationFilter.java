package com.softuni.eventem.jwt;

import com.softuni.eventem.exceptions.InvalidAuthenticationTokenException;
import io.jsonwebtoken.JwtException;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.INVALID_JWT_TOKEN_ERROR;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
private final JwtTokenUtil jwtTokenUtil;
  private final UserDetailsService userDetailsService;

  @Autowired
  public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws
    ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String username;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    try {
      jwt = authHeader.substring(7);
      username = jwtTokenUtil.getUsernameFromToken(jwt);
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(jwt, userDetails)) {
          UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
      filterChain.doFilter(request, response);
    } catch (JwtException e) {
      logger.error(e.getMessage());
      throw new InvalidAuthenticationTokenException(INVALID_JWT_TOKEN_ERROR);
    }
  }
}
