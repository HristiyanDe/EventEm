package com.softuni.eventem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.request.VenueRequest;
import com.softuni.eventem.services.impl.VenueServiceImpl;
import com.softuni.eventem.utils.VenueFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class VenueControllerTest {
  private MockMvc mockMvc;
  private VenueRequest venueRequest;
  private VenueEntity venueEntity;
  @Mock
  private VenueServiceImpl venueService;
  @InjectMocks
  private VenueController venueController;

  @Before
  public void setup(){
  venueRequest = VenueFactory.getVenueRequest();
  venueEntity = VenueFactory.getVenueEntity();
  mockMvc = MockMvcBuilders.standaloneSetup(venueController).build();
  }
  @Test
public void testCreateVenue_success() throws Exception{
    ObjectMapper objectMapper =new ObjectMapper();
    String json = objectMapper.writeValueAsString(venueRequest);
    when(venueService.createVenue(any())).thenReturn(venueEntity);
    mockMvc.perform(post("/api/venues")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(json))
      .andExpect(status().isCreated())
      .andExpect(header().string("Location", "/10"));

  }

}
