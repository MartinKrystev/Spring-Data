package com.example.json_processing.domain.products_shop.dtos.users;

import com.example.json_processing.domain.products_shop.dtos.products.ProductBasicDTO;
import com.example.json_processing.domain.products_shop.dtos.products.ProductDTO;
import com.example.json_processing.domain.products_shop.dtos.products.ProductsSoldWithCountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String firstName;

    private String lastName;

    private Integer age;

    private Set<ProductDTO> soldProducts = new HashSet<>();

    private Set<ProductDTO> boughtProducts = new HashSet<>();

    private Set<UserDTO> friends = new HashSet<>();

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public UserWithProductsDTO toUserWithProductsDTO(){
        return new UserWithProductsDTO(firstName, lastName, age, toProductsSoldWithCountDTO());
    }

    public ProductsSoldWithCountDTO toProductsSoldWithCountDTO() {
        return new ProductsSoldWithCountDTO(soldProducts
                .stream()
                .map(this::toProductBasicDTO)
                .collect(Collectors.toList()));
    }

    public ProductBasicDTO toProductBasicDTO(ProductDTO productDTO) {
        return new ProductBasicDTO(productDTO.getName(), productDTO.getPrice());
    }

}
