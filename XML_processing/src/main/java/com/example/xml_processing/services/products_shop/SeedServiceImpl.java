package com.example.xml_processing.services.products_shop;

import com.example.xml_processing.domain.products_shop.dtos.CategoriesImportWrapperDTO;
import com.example.xml_processing.domain.products_shop.dtos.ProductImportWrapperDTO;
import com.example.xml_processing.domain.products_shop.dtos.UserImportDTO;
import com.example.xml_processing.domain.products_shop.dtos.UserImportWrapperDTO;
import com.example.xml_processing.domain.products_shop.entities.Category;
import com.example.xml_processing.domain.products_shop.entities.Product;
import com.example.xml_processing.domain.products_shop.entities.User;
import com.example.xml_processing.repositories.products_shop.CategoryRepository;
import com.example.xml_processing.repositories.products_shop.ProductRepository;
import com.example.xml_processing.repositories.products_shop.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.xml_processing.constants.Paths.*;

@Service
public class SeedServiceImpl implements SeedService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public SeedServiceImpl(CategoryRepository categoryRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository,
                           ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override

    public void seedUsers() throws IOException, JAXBException {
        if (this.userRepository.count() == 0) {
            final FileReader fileReader = new FileReader(USERS_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(UserImportWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserImportWrapperDTO usersDto = (UserImportWrapperDTO) unmarshaller.unmarshal(fileReader);

            List<User> users = usersDto.getUsers()
                    .stream()
                    .map(userDto -> mapper.map(userDto, User.class))
                    .toList();

            this.userRepository.saveAllAndFlush(users);
            fileReader.close();
        }
    }

    @Override
    public void seedCategories() throws IOException, JAXBException {
        if (this.categoryRepository.count() == 0) {
            final FileReader fileReader = new FileReader(CATEGORIES_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(CategoriesImportWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CategoriesImportWrapperDTO categoriesImportWrapperDTO = (CategoriesImportWrapperDTO) unmarshaller.unmarshal(fileReader);

            List<Category> categories = categoriesImportWrapperDTO.getCategories()
                    .stream()
                    .map(categoryImportDTO -> mapper.map(categoryImportDTO, Category.class))
                    .toList();

            this.categoryRepository.saveAllAndFlush(categories);
            fileReader.close();
        }
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {

        if (this.productRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PRODUCTS_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(ProductImportWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProductImportWrapperDTO productImportWrapperDTO = (ProductImportWrapperDTO) unmarshaller.unmarshal(fileReader);

            List<Product> products = productImportWrapperDTO.getProducts()
                    .stream()
                    .map(productImportDTO -> mapper.map(productImportDTO, Product.class))
                    .map(this::setRandomSeller)
                    .map(this::setRandomBuyer)
                    .map(this::setRandomCategories)
                    .toList();

            this.productRepository.saveAllAndFlush(products);
            fileReader.close();
        }

    }

    private Product setRandomCategories(Product product) {
        Random random = new Random();

        int numberOfCategories = random.nextInt((int) this.categoryRepository.count());

        Set<Category> categories = new HashSet<>();

        IntStream.range(1, numberOfCategories)
                .forEach(n -> {
                    Category category = this.categoryRepository.getRandomEntity()
                            .orElseThrow(NoSuchElementException::new);
                    categories.add(category);
                });

        product.setCategories(categories);
        return product;
    }

    private Product setRandomBuyer(Product product) {

        if (product.getPrice().compareTo(BigDecimal.valueOf(800L)) > 0) {
            User buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

            while (buyer.equals(product.getSeller())) {
                buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
            }
            product.setBuyer(buyer);
        }
        return product;
    }

    private Product setRandomSeller(Product product) {
        final User seller = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
        product.setSeller(seller);
        return product;
    }


}

