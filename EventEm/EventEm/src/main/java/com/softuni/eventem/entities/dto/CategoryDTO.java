package com.softuni.eventem.entities.dto;

public class CategoryDTO {
  private String categoryName;

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  @Override
  public String toString() {
    return "CategoryDTO{" +
           "categoryName='" + categoryName + '\'' +
           '}';
  }
}
