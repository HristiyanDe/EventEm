package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.UserDetailsImpl;
import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.request.UpdateUserRoleRequest;
import com.softuni.eventem.entities.request.UpdateUserSecurityInfoRequest;
import com.softuni.eventem.repositories.projection.AdminUserListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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

  @Query("UPDATE UserDetailsImpl u SET u.username = :#{#updateUserSecurityInfoRequest.username}, u.password = :#{#updateUserSecurityInfoRequest.newPassword} " +
         "WHERE u.user.id = :userId")
  @Modifying
  int updateUserDetails(
    @Param("userId") Long id, @Param("updateUserSecurityInfoRequest")
  UpdateUserSecurityInfoRequest updateUserSecurityInfoRequest);

  Collection<AdminUserListDTO> findByUsernameContaining(String username);
}
