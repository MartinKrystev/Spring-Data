package com.example.springdataintroexercise.services.category;

import com.example.springdataintroexercise.domain.models.Category;
import com.example.springdataintroexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isDataSeeded() {
        return this.categoryRepository.count() > 0;
    }

    @Override
    public void seedCategories(List<Category> categories) {
        this.categoryRepository.saveAll(categories);
    }

    @Override
    public Set<Category> getRandomCategories() {
        long count = this.categoryRepository.count();
        if (count != 0) {
            long randomId = new Random().nextLong(1L, count) + 1L;
            return Set.of(this.categoryRepository.findById(randomId).orElseThrow(NoSuchElementException::new));
        }
        throw new RuntimeException();
    }
}
