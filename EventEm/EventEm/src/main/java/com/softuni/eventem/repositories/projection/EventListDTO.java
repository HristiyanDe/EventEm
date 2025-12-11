package com.softuni.eventem.repositories.projection;

import java.time.LocalDate;
import java.util.UUID;

public interface EventListDTO {
  UUID getId();
  String getName();
  LocalDate getStartDate();
}
