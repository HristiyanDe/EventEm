package com.softuni.eventem.services;

import com.softuni.eventem.entities.EventEntity;
import com.softuni.eventem.entities.request.EventRequest;
import com.softuni.eventem.repositories.projection.EventListDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface EventService {

  EventEntity createEvent(EventRequest eventRequest);

 List<EventListDTO> findEventsByName(String name);
}
