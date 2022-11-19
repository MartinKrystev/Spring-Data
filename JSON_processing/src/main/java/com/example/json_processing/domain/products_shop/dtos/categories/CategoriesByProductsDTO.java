package com.example.json_processing.domain.products_shop.dtos.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesByProductsDTO {

    private String name;

    private Long productCount;

    private Double averagePrice;

    private BigDecimal totalRevenue;
}
