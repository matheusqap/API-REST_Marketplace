package com.github.qualquercoisavinteconto.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Category;
import com.github.qualquercoisavinteconto.repositories.CategoryRepository;
import com.github.qualquercoisavinteconto.requests.CategoryRequest;
import com.github.qualquercoisavinteconto.services.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) throws ResourceNotFoundException {
        return categoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Category category = this.findById(id);
        categoryRepository.delete(category);
    }

    @Override
    public void update(Long id, CategoryRequest categoryDTO) throws ResourceNotFoundException{
        Category category = this.findById(id);
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
    }

}
