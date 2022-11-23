package com.example.xml_processing.services.products_shop;

import com.example.xml_processing.domain.products_shop.dtos.*;
import com.example.xml_processing.repositories.products_shop.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.xml_processing.constants.Paths.USERS_AND_PRODUCTS_XML_PATH;
import static com.example.xml_processing.constants.Paths.USERS_WITH_SOLD_PRODUCTS_XML_PATH;
import static com.example.xml_processing.constants.Utils.writeXmlIntoFile;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public List<UsersWithSoldProductsDTO> findAllBySoldProductsIsNotNullOrderByLastName() throws IOException, JAXBException {

        final List<UsersWithSoldProductsDTO> usersWithSoldProductsDTO = this.userRepository.findAllBySoldProductsIsNotNullOrderByLastName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> mapper.map(user, UsersWithSoldProductsDTO.class))
                .collect(Collectors.toList());

        UsersWithSoldProductsWrapperDTO users = new UsersWithSoldProductsWrapperDTO(usersWithSoldProductsDTO);

        writeXmlIntoFile(users, USERS_WITH_SOLD_PRODUCTS_XML_PATH);
        return usersWithSoldProductsDTO;

    }

    @Override
    public UsersAndProductsWrapperDTO getAllUsersWithSoldProducts() throws JAXBException, IOException {
        List<UserWithProductsDTO> usersAndProducts = this.userRepository.getAllUsersWithSoldProducts()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .map(UserDTO::toUserWithProductsDTO)
                .collect(Collectors.toList());

        UsersAndProductsWrapperDTO usersAndProductsDTO = new UsersAndProductsWrapperDTO(usersAndProducts);

        writeXmlIntoFile(usersAndProductsDTO, USERS_AND_PRODUCTS_XML_PATH);
        return usersAndProductsDTO;
    }
}
