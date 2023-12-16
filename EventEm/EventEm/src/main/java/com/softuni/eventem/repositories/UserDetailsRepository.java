package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsImpl, Long> {
  Optional<UserDetailsImpl> findByUsername(String username);


}
