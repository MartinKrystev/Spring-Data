package com.example.json_processing.domain.products_shop.dtos.users;

import com.example.json_processing.domain.products_shop.dtos.products.ProductsSoldDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersWithSoldProductsDTO {

    private String firstName;

    private String lastName;

    private List<ProductsSoldDTO> soldProducts;
}
