package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.UserEntity;
import com.softuni.eventem.entities.request.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

  @Query("UPDATE UserEntity u SET u.firstName = :#{#userRequest.firstName}, " +
         "u.lastName = :#{#userRequest.lastName}," +
         "u.city = :#{#userRequest.city}, " +
         "u.address = :#{#userRequest.address}, " +
         "u.phone = :#{#userRequest.phone}, " +
         "u.email = :#{#userRequest.email} " +
         "WHERE u.id = :userId"
         )
  @Modifying
  @Transactional
  int updateUserProfile(@Param("userId") UUID id, @Param("userRequest") UserRequest userRequest);
}
