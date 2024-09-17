package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.CategoryModel;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryModel> getAllCategories();
    Optional<CategoryModel> getCategoryById(Long id);
    CategoryModel saveCategory(CategoryModel category);
    void deleteCategory(Long id);
}