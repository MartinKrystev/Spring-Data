package com.example.xml_processing.services.products_shop;

import com.example.xml_processing.domain.products_shop.dtos.UserWithProductsDTO;
import com.example.xml_processing.domain.products_shop.dtos.UsersAndProductsWrapperDTO;
import com.example.xml_processing.domain.products_shop.dtos.UsersWithSoldProductsDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UsersWithSoldProductsDTO> findAllBySoldProductsIsNotNullOrderByLastName() throws IOException, JAXBException;

    UsersAndProductsWrapperDTO getAllUsersWithSoldProducts() throws JAXBException, IOException;
}
