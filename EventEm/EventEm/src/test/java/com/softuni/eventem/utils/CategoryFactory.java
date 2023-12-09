package com.softuni.eventem.utils;

import com.softuni.eventem.entities.CategoryEntity;
import com.softuni.eventem.entities.request.CategoryRequest;

import static com.softuni.eventem.utils.CategoryConstants.CATEGORY_ID;
import static com.softuni.eventem.utils.CategoryConstants.CATEGORY_NAME;
//TODO: events for unit testing?

public final class CategoryFactory {
public static CategoryRequest getCategoryRequest(){
  CategoryRequest categoryRequest = new CategoryRequest();
  categoryRequest.setCategoryName(CATEGORY_NAME);
  return categoryRequest;
}
public static CategoryEntity getCategoryEntity(){
  CategoryEntity categoryEntity = new CategoryEntity();
  categoryEntity.setId(CATEGORY_ID);
  categoryEntity.setCategoryName(CATEGORY_NAME);
  return categoryEntity;
}
}
