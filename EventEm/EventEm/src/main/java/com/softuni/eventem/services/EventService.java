package com.softuni.eventem.services;

import com.softuni.eventem.entities.EventEntity;
import com.softuni.eventem.entities.request.EventRequest;

public interface EventService {

  EventEntity createEvent(EventRequest eventRequest);
}
