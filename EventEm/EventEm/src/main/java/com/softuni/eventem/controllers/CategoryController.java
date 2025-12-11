package com.softuni.eventem.controllers;

import com.softuni.eventem.entities.dto.CategoryDTO;
import com.softuni.eventem.entities.request.CategoryRequest;
import com.softuni.eventem.services.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Category Controller", description = "The Category API. Contains all the operations that can be performed on a category")
public class CategoryController {
  private final CategoryService categoryService;
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }
  @PostMapping
  public ResponseEntity<Void> createCategory(@RequestBody @Valid CategoryRequest categoryRequest)
  {
    System.out.println("in method");
    categoryService.createCategory(categoryRequest);
    return ResponseEntity.ok().build();
  }
  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getCategories()
  {
    return ResponseEntity.ok(categoryService.getCategories());
  }
}
