package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.CategoryEntity;
import com.softuni.eventem.entities.request.CategoryRequest;
import com.softuni.eventem.exceptions.CategoryAlreadyExistsException;
import com.softuni.eventem.exceptions.NoMatchingCategoriesWithNamesFoundException;
import com.softuni.eventem.repositories.CategoryRepository;
import com.softuni.eventem.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.softuni.eventem.constants.LoggerAndExceptionConstants.CATEGORY_ALREADY_EXISTS_ERROR_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.CATEGORY_CREATED_MESSAGE;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.ENTITY_ALREADY_EXISTS_ERROR;
import static com.softuni.eventem.constants.LoggerAndExceptionConstants.NO_MATCHING_CATEGORIES_FOUND_ERROR_MESSAGE;

@Service
public class CategoryServiceImpl implements CategoryService {
  public static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
  private final CategoryRepository categoryRepository;
  private final ModelMapper modelMapper;

  public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
    this.categoryRepository = categoryRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public CategoryEntity createCategory(CategoryRequest categoryRequest) {
    try {
      CategoryEntity category = categoryRepository.save(
        modelMapper.map(categoryRequest, CategoryEntity.class)
      );
      logger.info(String.format(
        CATEGORY_CREATED_MESSAGE, category));
      return category;
    } catch (DataIntegrityViolationException e) {
      logger.error(String.format(ENTITY_ALREADY_EXISTS_ERROR, categoryRequest));
      throw new CategoryAlreadyExistsException(CATEGORY_ALREADY_EXISTS_ERROR_MESSAGE);
    }
  }

  @Override
  public List<CategoryEntity> getCategoriesWithNames(List<String> categoryNames) {

  return categoryRepository.findByCategoryNameIn(categoryNames).orElseThrow(
      () ->
        new NoMatchingCategoriesWithNamesFoundException(
          NO_MATCHING_CATEGORIES_FOUND_ERROR_MESSAGE));
  }
}
