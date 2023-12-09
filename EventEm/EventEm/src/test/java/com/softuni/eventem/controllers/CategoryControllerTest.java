package com.softuni.eventem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softuni.eventem.entities.CategoryEntity;
import com.softuni.eventem.entities.request.CategoryRequest;
import com.softuni.eventem.exceptions.VenueAlreadyExistsException;
import com.softuni.eventem.services.impl.CategoryServiceImpl;
import com.softuni.eventem.utils.CategoryFactory;
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

import static com.softuni.eventem.utils.CategoryConstants.DUPLICATE_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

  private MockMvc mockMvc;
  private CategoryRequest categoryRequest;
  private CategoryEntity categoryEntity;
  @Mock
  private CategoryServiceImpl categoryService;
  @InjectMocks
  private CategoryController categoryController;

  @Before
  public void setup() {
    categoryRequest = CategoryFactory.getCategoryRequest();
    categoryEntity = CategoryFactory.getCategoryEntity();
    mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
  }

  @Test
  public void testCreateCategory_success() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(categoryRequest);
    when(categoryService.createCategory(any())).thenReturn(categoryEntity);
    mockMvc.perform(post("/api/categories")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(json))
           .andExpect(status().isCreated())
           .andExpect(header().string("Location", "/10"));
  }

  @Test(expected = ServletException.class)
  public void testCreateCategory_throws() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(categoryRequest);
    when(categoryService.createCategory(any()))
      .thenThrow(new VenueAlreadyExistsException(DUPLICATE_MESSAGE));

    mockMvc.perform(post("/api/categories")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(json))
           .andExpect(status().isBadRequest())
           .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(DUPLICATE_MESSAGE)));
  }
}
