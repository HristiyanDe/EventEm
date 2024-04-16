package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.UserDetailsImpl;
import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.entities.request.UpdateUserUsernameRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsImpl, UserEntity> {

  Optional<UserDetailsImpl> findByUsername(String username);

  @Query("SELECT u FROM UserDetailsImpl u WHERE u.user.id = :userId")
  Optional<UserDetailsImpl> findByUserId(Long userId);

  @Query("UPDATE UserDetailsImpl u SET u.role = :#{#updateUserRoleRequest.role} " +
         "WHERE u.user.id = :userId")
  @Modifying
  int updateUserRole(
    @Param("userId") Long id, @Param("updateUserRoleRequest") UpdateUserRoleRequest updateUserRoleRequest);

  @Query("UPDATE UserDetailsImpl u SET u.username = :#{#updateUserUsernameRequest.username} " +
         "WHERE u.user.id = :userId")
  @Modifying
  int updateUserUsername(
    @Param("userId") Long id, @Param("updateUserUsernameRequest") UpdateUserUsernameRequest updateUserUsernameRequest);
}
