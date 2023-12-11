package com.softuni.eventem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softuni.eventem.entities.TicketEntity;
import com.softuni.eventem.entities.request.TicketRequest;
import com.softuni.eventem.exceptions.TicketAlreadyExistsException;
import com.softuni.eventem.services.impl.TicketServiceImpl;
import com.softuni.eventem.utils.OrganizationConstants;
import com.softuni.eventem.utils.TicketConstants;
import com.softuni.eventem.utils.TicketFactory;
import jakarta.servlet.ServletException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.softuni.eventem.utils.TicketConstants.DUPLICATE_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class TicketControllerTest {
  private MockMvc mockMvc;
  private TicketRequest ticketRequest;
  private TicketEntity ticketEntity;
  @Mock
  private TicketServiceImpl ticketService;
  @InjectMocks
  private TicketController ticketController;
  @Before
  public void setup(){
    ticketRequest = TicketFactory.getTicketRequest();
    ticketEntity = TicketFactory.getTicketEntity();
    mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
  }
  @Test
  public void testCreateTicket_success() throws Exception {
    ObjectMapper objectMapper =new ObjectMapper();
    String json = objectMapper.writeValueAsString(ticketRequest);
    when(ticketService.createTicket(any())).thenReturn(ticketEntity);
    mockMvc.perform(post("/api/tickets")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(json))
           .andExpect(status().isCreated())
           .andExpect(header().string("Location", "/10"));
  }
  @Test(expected = ServletException.class)
  public void testCreateTicket_ThrowsTicketAlreadyExists() throws Exception {
    ObjectMapper objectMapper =new ObjectMapper();
    String json = objectMapper.writeValueAsString(ticketRequest);
    when(ticketService.createTicket(any())).thenThrow(new TicketAlreadyExistsException(DUPLICATE_MESSAGE));
    mockMvc.perform(post("/api/tickets")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(json))
           .andExpect(status().isBadRequest())
           .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(TicketConstants.DUPLICATE_MESSAGE));
  }


}
