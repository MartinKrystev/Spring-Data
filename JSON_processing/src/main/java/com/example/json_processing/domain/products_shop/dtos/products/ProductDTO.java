package com.example.json_processing.domain.products_shop.dtos.products;

import com.example.json_processing.domain.products_shop.dtos.categories.CategoryDTO;
import com.example.json_processing.domain.products_shop.dtos.users.UserDTO;
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
