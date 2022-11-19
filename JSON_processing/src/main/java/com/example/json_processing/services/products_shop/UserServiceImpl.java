package com.example.json_processing.services.products_shop;

import com.example.json_processing.domain.products_shop.dtos.users.UserDTO;
import com.example.json_processing.domain.products_shop.dtos.users.UserWithProductsDTO;
import com.example.json_processing.domain.products_shop.dtos.users.UsersAndProductsDTO;
import com.example.json_processing.domain.products_shop.dtos.users.UsersWithSoldProductsDTO;
import com.example.json_processing.repositories.products_shop.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.json_processing.constants.Paths.USERS_AND_PRODUCTS_JSON_PATH;
import static com.example.json_processing.constants.Paths.USERS_WITH_SOLD_PRODUCTS_JSON_PATH;
import static com.example.json_processing.constants.Utils.MODEL_MAPPER;
import static com.example.json_processing.constants.Utils.writeJsonIntoFiles;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UsersWithSoldProductsDTO> findAllBySoldProductsIsNotNullOrderByLastName() throws IOException {
        final List<UsersWithSoldProductsDTO> usersWithSoldProductsDTO = this.userRepository.findAllBySoldProductsIsNotNullOrderByLastName()
                .orElseThrow(NoSuchElementException::new)
                .stream().map(user -> MODEL_MAPPER.map(user, UsersWithSoldProductsDTO.class))
                .collect(Collectors.toList());

        writeJsonIntoFiles(usersWithSoldProductsDTO, USERS_WITH_SOLD_PRODUCTS_JSON_PATH);
        return usersWithSoldProductsDTO;
    }

    @Override
    public UsersAndProductsDTO getAllUsersWithSoldProducts() throws IOException {
        List<UserWithProductsDTO> usersAndProducts = this.userRepository.getAllUsersWithSoldProducts()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> MODEL_MAPPER.map(user, UserDTO.class))
                .map(UserDTO::toUserWithProductsDTO)
                .collect(Collectors.toList());

        UsersAndProductsDTO usersAndProductsDTO = new UsersAndProductsDTO(usersAndProducts);

        writeJsonIntoFiles(usersAndProductsDTO, USERS_AND_PRODUCTS_JSON_PATH);

        return usersAndProductsDTO;
    }
}
