package com.example.json_processing.services.products_shop;

import com.example.json_processing.domain.products_shop.dtos.products.ProductDTO;
import com.example.json_processing.domain.products_shop.dtos.products.ProductInRangeWithNoBuyerDTO;
import com.example.json_processing.repositories.products_shop.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.json_processing.constants.Paths.PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH;
import static com.example.json_processing.constants.Utils.MODEL_MAPPER;
import static com.example.json_processing.constants.Utils.writeJsonIntoFiles;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductInRangeWithNoBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal low, BigDecimal high) throws IOException {
        List<ProductInRangeWithNoBuyerDTO> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, high)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(product -> MODEL_MAPPER.map(product, ProductDTO.class))
                .map(ProductDTO::toProductInRangeWithNoBuyerDTO)
                .collect(Collectors.toList());

        writeJsonIntoFiles(products, PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH);
        return products;
    }
}
