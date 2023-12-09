package com.softuni.eventem.services;

import com.softuni.eventem.entities.CategoryEntity;
import com.softuni.eventem.entities.request.CategoryRequest;

public interface CategoryService {
  CategoryEntity createCategory(CategoryRequest categoryRequest);

}
