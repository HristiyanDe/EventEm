package com.softuni.eventem.services.impl;

import com.softuni.eventem.entities.CategoryEntity;
import com.softuni.eventem.entities.VenueEntity;
import com.softuni.eventem.entities.request.CategoryRequest;
import com.softuni.eventem.exceptions.CategoryAlreadyExistsException;
import com.softuni.eventem.repositories.CategoryRepository;
import com.softuni.eventem.utils.CategoryFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

  private CategoryRequest categoryRequest;
  private CategoryEntity categoryEntity;
  @Mock
  private CategoryRepository categoryRepository;
  @Mock
  private ModelMapper modelMapper;
  @InjectMocks
  private CategoryServiceImpl categoryService;

  @Before
  public void setup(){
    categoryRequest = CategoryFactory.getCategoryRequest();
    categoryEntity = CategoryFactory.getCategoryEntity();
  }
  @Test
  public void testCreateCategory_success(){
    when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);
    when(modelMapper.map(categoryRequest, CategoryEntity.class)).thenReturn(categoryEntity);

    CategoryEntity category = categoryService.createCategory(categoryRequest);

    verify(categoryRepository, times(1)).save(categoryEntity);
    verify(modelMapper, times(1)).map(categoryRequest, CategoryEntity.class);

    assertEquals(10L,category.getId());
  }

  @Test(expected = CategoryAlreadyExistsException.class)
  public void testCreateCategory_ThrowsCategoryAlreadyExists()
  {
    when(categoryRepository.save(any(CategoryEntity.class))).thenThrow(DataIntegrityViolationException.class);
    when(modelMapper.map(categoryRequest, CategoryEntity.class)).thenReturn(categoryEntity);

    categoryService.createCategory(categoryRequest);

    verify(categoryRepository, times(1)).save(categoryEntity);
    verify(modelMapper, times(1)).map(categoryRequest, CategoryEntity.class);

  }
}
