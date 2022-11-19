package com.example.json_processing.services.products_shop;

import com.example.json_processing.domain.products_shop.dtos.users.UsersAndProductsDTO;
import com.example.json_processing.domain.products_shop.dtos.users.UsersWithSoldProductsDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UsersWithSoldProductsDTO> findAllBySoldProductsIsNotNullOrderByLastName() throws IOException;

    UsersAndProductsDTO getAllUsersWithSoldProducts() throws IOException;
}
