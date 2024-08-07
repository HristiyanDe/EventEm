package com.softuni.eventem.repositories;

import com.softuni.eventem.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
  Optional<List<CategoryEntity>> findByCategoryNameIn(List<String> categoryNames);
  @Query("SELECT new CategoryEntity(c.categoryName) FROM CategoryEntity c")
  List<CategoryEntity> findCategoryNames();
}
