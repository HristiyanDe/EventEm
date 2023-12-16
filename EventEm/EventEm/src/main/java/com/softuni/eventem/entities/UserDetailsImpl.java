package com.softuni.eventem.entities;

import com.softuni.eventem.entities.enums.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Entity
@Table(name = "user_details")
public class UserDetailsImpl implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String username;
private String password;
@Enumerated(EnumType.STRING)
@Column(name = "user_role")
private UserRoleEnum role;
@OneToOne
private UserEntity user;

  public UserDetailsImpl( String username, String password, UserRoleEnum role, UserEntity user) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.user = user;
  }

  public UserDetailsImpl() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
