package com.softuni.eventem.services;

import com.softuni.eventem.entities.CategoryEntity;
import com.softuni.eventem.entities.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
  CategoryEntity createCategory(CategoryRequest categoryRequest);
  List<CategoryEntity> getCategoriesWithNames(List<String> categoryNames);

}
