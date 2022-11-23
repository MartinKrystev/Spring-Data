package com.example.xml_processing.domain.products_shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private BigDecimal price;

    private UserDTO buyer;
    private UserDTO seller;

    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductInRangeWithNoBuyerDTO toProductInRangeWithNoBuyerDTO(){
        return new ProductInRangeWithNoBuyerDTO(name, price, seller.getFullName());
    }
}
