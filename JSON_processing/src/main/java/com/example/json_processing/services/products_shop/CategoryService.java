package com.example.json_processing.services.products_shop;

import com.example.json_processing.domain.products_shop.dtos.categories.CategoriesByProductsDTO;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<CategoriesByProductsDTO> getCategoriesByProducts() throws IOException;

}
