package com.example.xml_processing.services.products_shop;

import com.example.xml_processing.domain.products_shop.dtos.ProductInRangeWithNoBuyerDTO;
import com.example.xml_processing.domain.products_shop.entities.Product;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductInRangeWithNoBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal low, BigDecimal high) throws IOException, JAXBException;
}
