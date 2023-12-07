package com.softuni.eventem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softuni.eventem.entities.OrganizationEntity;
import com.softuni.eventem.entities.request.OrganizationRequest;
import com.softuni.eventem.entities.request.VenueRequest;
import com.softuni.eventem.exceptions.OrganizationAlreadyExistsException;
import com.softuni.eventem.exceptions.VenueAlreadyExistsException;
import com.softuni.eventem.services.impl.OrganizationServiceImpl;
import com.softuni.eventem.utils.OrganizationFactory;
import com.softuni.eventem.utils.VenueFactory;
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

import static com.softuni.eventem.utils.OrganizationConstants.DUPLICATE_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class OrganizationControllerTest {
  private MockMvc mockMvc;
  private OrganizationRequest organizationRequest;
  private OrganizationEntity organizationEntity;
  @Mock
  private OrganizationServiceImpl organizationService;
  @InjectMocks
  private OrganizationController organizationController;

  @Before
  public void setup(){
    organizationRequest = OrganizationFactory.getOrganizationRequest();
    organizationEntity = OrganizationFactory.getOrganizationEntity();
    mockMvc = MockMvcBuilders.standaloneSetup(organizationController).build();
  }

  @Test
  public void testCreateOrganization_success() throws Exception{
    ObjectMapper objectMapper =new ObjectMapper();
    String json = objectMapper.writeValueAsString(organizationRequest);
    when(organizationService.createOrganization(any())).thenReturn(organizationEntity);
    mockMvc.perform(post("/api/organizations")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(json))
           .andExpect(status().isCreated())
           .andExpect(header().string("Location", "/10"));

  }
  @Test(expected = ServletException.class)
  public void testCreateOrganization_throws() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(organizationRequest);
    when(organizationService.createOrganization(any()))
      .thenThrow(new OrganizationAlreadyExistsException(DUPLICATE_MESSAGE));

    mockMvc.perform(post("/api/organizations")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(json))
           .andExpect(status().isBadRequest())
           .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(DUPLICATE_MESSAGE)));
  }

}
