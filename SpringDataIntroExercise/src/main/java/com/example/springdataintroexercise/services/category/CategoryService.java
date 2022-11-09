package com.example.springdataintroexercise.services.category;

import com.example.springdataintroexercise.domain.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    boolean isDataSeeded();

    void seedCategories(List<Category> categories);

    Set<Category> getRandomCategories();
}
