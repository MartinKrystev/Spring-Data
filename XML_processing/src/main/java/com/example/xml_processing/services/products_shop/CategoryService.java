package com.example.xml_processing.services.products_shop;

import com.example.xml_processing.domain.products_shop.dtos.CategoriesByProductsDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<CategoriesByProductsDTO> getCategoriesByProducts() throws IOException, JAXBException;
}
