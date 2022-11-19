package com.example.json_processing.services.products_shop;

import com.example.json_processing.domain.products_shop.dtos.products.ProductInRangeWithNoBuyerDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService{
    List<ProductInRangeWithNoBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal low, BigDecimal high) throws IOException;

}
