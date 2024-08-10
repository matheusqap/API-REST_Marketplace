package com.github.qualquercoisavinteconto.services;

import java.util.List;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Category;
import com.github.qualquercoisavinteconto.requests.CategoryRequest;

public interface CategoryService {

  Category save(Category category);
  Category findById(Long id) throws ResourceNotFoundException;
  List<Category> findByName(String name);
  List<Category> findAll();
  void delete(Long id) throws ResourceNotFoundException;
  void update(Long id, CategoryRequest categoryDTO) throws ResourceNotFoundException;
  
}
