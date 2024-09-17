package com.example.SpringAppV3.repository;

import com.example.SpringAppV3.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

}
