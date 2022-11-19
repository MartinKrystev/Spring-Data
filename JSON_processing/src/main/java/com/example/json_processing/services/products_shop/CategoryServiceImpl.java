package com.example.json_processing.services.products_shop;

import com.example.json_processing.domain.products_shop.dtos.categories.CategoriesByProductsDTO;
import com.example.json_processing.repositories.products_shop.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.json_processing.constants.Paths.CATEGORIES_BY_PRODUCTS_JSON_PATH;
import static com.example.json_processing.constants.Utils.writeJsonIntoFiles;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoriesByProductsDTO> getCategoriesByProducts() throws IOException {
        List<CategoriesByProductsDTO> categoriesByProductsDTOS = this.categoryRepository.getCategoriesByProducts().orElseThrow(NoSuchElementException::new);

        writeJsonIntoFiles(categoriesByProductsDTOS, CATEGORIES_BY_PRODUCTS_JSON_PATH);

        return categoriesByProductsDTOS;
    }
}
