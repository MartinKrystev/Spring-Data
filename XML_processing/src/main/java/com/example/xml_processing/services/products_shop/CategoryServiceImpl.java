package com.example.xml_processing.services.products_shop;

import com.example.xml_processing.domain.products_shop.dtos.CategoriesByProductsDTO;
import com.example.xml_processing.domain.products_shop.dtos.CategoriesProductsWrapperDTO;
import com.example.xml_processing.repositories.products_shop.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.xml_processing.constants.Paths.CATEGORIES_BY_PRODUCTS_XML_PATH;
import static com.example.xml_processing.constants.Utils.writeXmlIntoFile;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoriesByProductsDTO> getCategoriesByProducts() throws IOException, JAXBException {

        List<CategoriesByProductsDTO> categoriesByProductsDTOS = this.categoryRepository
                .getCategoriesByProducts()
                .orElseThrow(NoSuchElementException::new);

        CategoriesProductsWrapperDTO categories = new CategoriesProductsWrapperDTO(categoriesByProductsDTOS);

        writeXmlIntoFile(categories, CATEGORIES_BY_PRODUCTS_XML_PATH);
        return categoriesByProductsDTOS;
    }
}
