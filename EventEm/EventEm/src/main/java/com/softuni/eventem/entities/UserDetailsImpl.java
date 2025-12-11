package com.softuni.eventem.entities;

import com.softuni.eventem.entities.enums.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_details")
public class UserDetailsImpl implements UserDetails {
  @Id
  private UUID userId;
  @MapsId
  @OneToOne
  @JoinColumn(name = "user_id")
private UserEntity user;
private String username;
private String password;
@Enumerated(EnumType.STRING)
@Column(name = "user_role")
private UserRoleEnum role;
  @ManyToMany
  @JoinTable(
    name = "users_organizations",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "organization_id")
  )
  List<OrganizationEntity> organizations;
  @Column(name = "enabled")
  private boolean enabled;

  public UserDetailsImpl( String username, String password, UserRoleEnum role, UserEntity user, boolean enabled) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.user = user;
    this.enabled=enabled;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public UserDetailsImpl() {
  }


  public List<OrganizationEntity> getOrganizations() {
    return organizations;
  }

  public void setOrganizations(List<OrganizationEntity> organizations) {
    this.organizations = organizations;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserRoleEnum getRole() {
    return role;
  }

  public void setRole(UserRoleEnum role) {
    this.role = role;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

}
