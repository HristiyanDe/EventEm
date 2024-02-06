package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<VenueEntity,Long> {

  @Query(
    "SELECT v FROM VenueEntity v WHERE (:venueName IS NULL OR v.name LIKE %:venueName%)" +
    "AND (:venueAddress IS NULL OR v.address LIKE %:venueAddress%)" +
    "AND (:venueCity IS NULL OR v.city LIKE  %:venueCity%)")
  List<VenueEntity> findAllVenuesFiltered(
    @Param("venueName") String venueName, @Param("venueAddress") String venueAddress, @Param("venueCity") String venueCity);
}
