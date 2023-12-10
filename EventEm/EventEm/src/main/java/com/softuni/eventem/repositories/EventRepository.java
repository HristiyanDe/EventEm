package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
  @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END " +
         "FROM EventEntity e " +
         "WHERE e.venue.id = :venueId " +
         "AND (" +
         "   (e.startDate BETWEEN :newEventStartDate AND :newEventEndDate) " +
         "   OR (e.endDate BETWEEN :newEventStartDate AND :newEventEndDate) " +
         "   OR (:newEventStartDate BETWEEN e.startDate AND e.endDate) " +
         "   OR (:newEventEndDate BETWEEN e.startDate AND e.endDate)" +
         ")")
  boolean hasConflictingEventsByVenueAndDateRange(
    @Param("venueId") Long venueId,
    @Param("newEventStartDate") LocalDate newEventStartDate,
    @Param("newEventEndDate") LocalDate newEventEndDate
  );

}
