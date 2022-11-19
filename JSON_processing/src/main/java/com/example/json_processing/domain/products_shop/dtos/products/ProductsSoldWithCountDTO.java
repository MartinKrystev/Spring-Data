package com.example.json_processing.domain.products_shop.dtos.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsSoldWithCountDTO {
    private Integer count;
    private List<ProductBasicDTO> products;
    public ProductsSoldWithCountDTO(List<ProductBasicDTO> products) {
        this.products = products;
        this.count = products.size();
    }
}
