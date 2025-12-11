package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.UserDetailsImpl;
import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.enums.UserRoleEnum;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.entities.request.UpdateUserSecurityInfoRequest;
import com.softuni.eventem.repositories.projection.AdminUserListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsImpl, UserEntity> {

  Optional<UserDetailsImpl> findByUsername(String username);

  @Query("SELECT u FROM UserDetailsImpl u WHERE u.user.id = :userId")
  Optional<UserDetailsImpl> findByUserId(UUID userId);

  @Query("UPDATE UserDetailsImpl u SET u.role = :#{#updateUserRoleRequest.role} " +
         "WHERE u.user.id = :userId")
  @Modifying
  int updateUserRole(
    @Param("userId") UUID id, @Param("updateUserRoleRequest") UpdateUserRoleRequest updateUserRoleRequest);

  @Query("UPDATE UserDetailsImpl u SET u.username = :#{#updateUserSecurityInfoRequest.username}, u.password = :#{#updateUserSecurityInfoRequest.newPassword} " +
         "WHERE u.user.id = :userId")
  @Modifying
  int updateUserDetails(
    @Param("userId") UUID id, @Param("updateUserSecurityInfoRequest")
  UpdateUserSecurityInfoRequest updateUserSecurityInfoRequest);

  Collection<AdminUserListDTO> findByUsernameContaining(String username);
  @Modifying
  @Transactional
  @Query("UPDATE UserDetailsImpl u SET u.enabled = NOT u.enabled WHERE u.username = :username")
  int updateUserEnabled(@Param(("username")) String username);

  @Modifying
  @Transactional
  @Query("UPDATE UserDetailsImpl u SET u.role = :role WHERE u.username = :username")
  int updateUserRole(
    @Param("username") String username,
    @Param("role") UserRoleEnum role
  );
}
