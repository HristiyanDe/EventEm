package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.UserDetailsImpl;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsImpl, Long> {

  Optional<UserDetailsImpl> findByUsername(String username);

  @Query("SELECT u FROM UserDetailsImpl u WHERE u.user.id = :userId")
  Optional<UserDetailsImpl> findByUserId(Long userId);

  @Query("UPDATE UserDetailsImpl u SET u.role = :#{#updateUserRoleRequest.role} " +
         "WHERE u.user.id = :userId")
  @Modifying
  int updateUserRole(
    @Param("userId") Long id, @Param("updateUserRoleRequest") UpdateUserRoleRequest updateUserRoleRequest);
}
