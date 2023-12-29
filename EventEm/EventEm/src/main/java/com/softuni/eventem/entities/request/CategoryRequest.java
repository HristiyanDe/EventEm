package com.softuni.eventem.entities.request;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequest {
  @NotBlank(message = "Category name must not be empty")
  private String categoryName;

  public CategoryRequest() {
  }

  public CategoryRequest(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  @Override
  public String toString() {
    return "CategoryRequest{" +
           "categoryName='" + categoryName + '\'' +
           '}';
  }
}
