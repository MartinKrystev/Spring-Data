package com.example.json_processing;

import com.example.json_processing.services.car_dealer.*;
import com.example.json_processing.services.products_shop.CategoryService;
import com.example.json_processing.services.products_shop.ProductService;
import com.example.json_processing.services.products_shop.SeedService;
import com.example.json_processing.services.products_shop.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
public class CommandRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CarDealerSeedService carDealerSeedService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;
    private final SaleService saleService;


    @Autowired
    public CommandRunner(SeedService seedService,
                         ProductService productService,
                         UserService userService,
                         CategoryService categoryService,
                         CarDealerSeedService carDealerSeedService,
                         CustomerService customerService,
                         CarService carService,
                         SupplierService supplierService,
                         SaleService saleService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.carDealerSeedService = carDealerSeedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
        this.saleService = saleService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //TASK #1 products_shop
        this.seedService.seedAll();

        //Query 1 – Products in Range
        this.productService.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        //Query 2 – Successfully Sold Products
        this.userService.findAllBySoldProductsIsNotNullOrderByLastName();

        //Query 3 - Categories by Products Count
        this.categoryService.getCategoriesByProducts();

        //Query 4 – Users and Products
        this.userService.getAllUsersWithSoldProducts();


        //TASK#2 car_dealer
        this.carDealerSeedService.seedAll();

        //Query 1 – Ordered Customers
        this.customerService.getAllCustomersOrderedByBirthDate();

        //Query 2 – Cars from Make Toyota
        this.carService.getAllToyotaCars();

        //Query 3 – Local Suppliers
        this.supplierService.getSuppliersByIsImporterIsFalse();

        //Query 4 – Cars with Their List of Parts
        this.carService.getAllByIdGreaterThan(0L);

        //Query 5 – Total Sales by Customer
        this.customerService.getAllCustomersWithSales();

        //Query 6 – Sales with Applied Discount
        this.saleService.getAllSalesWithDiscounts();
    }
}
