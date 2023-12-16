package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {


}
