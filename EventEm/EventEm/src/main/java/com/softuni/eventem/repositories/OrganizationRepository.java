package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.repositories.projection.OrganizationBasicView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
  @Query("SELECT o FROM OrganizationEntity o JOIN o.users u WHERE u.userId = :userId")
  List<OrganizationBasicView> findAllByUserId(@Param("userId") Long userId);

}
