package com.example.xml_processing.services.products_shop;

import com.example.xml_processing.domain.products_shop.dtos.ProductDTO;
import com.example.xml_processing.domain.products_shop.dtos.ProductInRangeWithNoBuyerDTO;
import com.example.xml_processing.domain.products_shop.dtos.ProductsInRangeWithNoBuyerDTO;
import com.example.xml_processing.repositories.products_shop.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.xml_processing.constants.Paths.PRODUCTS_WITH_NO_BUYERS_IN_RANGE_XML_PATH;
import static com.example.xml_processing.constants.Utils.writeXmlIntoFile;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductInRangeWithNoBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal low, BigDecimal high) throws IOException, JAXBException {
        List<ProductInRangeWithNoBuyerDTO> products = this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, high)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .map(ProductDTO::toProductInRangeWithNoBuyerDTO).toList();

        ProductsInRangeWithNoBuyerDTO productsWrapper = new ProductsInRangeWithNoBuyerDTO(products);

        writeXmlIntoFile(productsWrapper, PRODUCTS_WITH_NO_BUYERS_IN_RANGE_XML_PATH);
        return products;
    }
}
