package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.request.CategoryRequest;
import com.softuni.eventem.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }
  @PostMapping
  public ResponseEntity<Void> createCategory(@RequestBody @Valid CategoryRequest categoryRequest)
  {
    return ResponseEntity.created(
                           UriComponentsBuilder
                             .fromUriString("/{id}")
                             .buildAndExpand(
                               categoryService
                                 .createCategory(categoryRequest)
                                 .getId())
                             .toUri())
                         .build();
  }
}
