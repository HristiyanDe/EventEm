package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
  //Optional<List<TicketEntity>> findTic

}
