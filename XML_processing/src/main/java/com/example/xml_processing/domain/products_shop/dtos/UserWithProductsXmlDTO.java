package com.example.xml_processing.domain.products_shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithProductsXmlDTO {

    private List<UserWithProductsDTO> users;
}
