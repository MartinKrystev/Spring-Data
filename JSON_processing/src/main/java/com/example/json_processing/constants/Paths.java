package com.example.json_processing.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    // products_shop
    public static final Path USERS_JSON_PATH = Path.of("src", "main", "resources", "dbContent", "users.json");
    public static final Path CATEGORIES_JSON_PATH = Path.of("src", "main", "resources", "dbContent", "categories.json");
    public static final Path PRODUCTS_JSON_PATH = Path.of("src", "main", "resources", "dbContent", "products.json");

    public static final Path PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH = Path.of("src", "main", "resources", "outputs", "products_in_range.json");
    public static final Path USERS_WITH_SOLD_PRODUCTS_JSON_PATH = Path.of("src", "main", "resources", "outputs", "users-sold-products.json");
    public static final Path CATEGORIES_BY_PRODUCTS_JSON_PATH = Path.of("src", "main", "resources", "outputs", "categories-by-products.json");
    public static final Path USERS_AND_PRODUCTS_JSON_PATH = Path.of("src", "main", "resources", "outputs", "users-and-products.json");

    // car_dealer
    public static final Path SUPPLIERS_JSON_PATH = Path.of("src", "main", "resources", "dbContent", "suppliers.json");
    public static final Path PARTS_JSON_PATH = Path.of("src", "main", "resources", "dbContent", "parts.json");
    public static final Path CARS_JSON_PATH = Path.of("src", "main", "resources", "dbContent", "cars.json");
    public static final Path CUSTOMERS_JSON_PATH = Path.of("src", "main", "resources", "dbContent", "customers.json");

    public static final Path ORDERED_CUSTOMERS_JSON_PATH = Path.of("src", "main", "resources", "outputs", "ordered-customers.json");
    public static final Path TOYOTA_CARS_JSON_PATH = Path.of("src", "main", "resources", "outputs", "toyota-cars.json");
    public static final Path LOCAL_SUPPLIER_JSON_PATH = Path.of("src", "main", "resources", "outputs", "local-suppliers.json");
    public static final Path CARS_AND_PARTS_JSON_PATH = Path.of("src", "main", "resources", "outputs", "cars-and-parts.json");
    public static final Path CUSTOMERS_TOTAL_SALES_JSON_PATH = Path.of("src", "main", "resources", "outputs", "customers-total-sales.json");
    public static final Path SALES_DISCOUNTS_JSON_PATH = Path.of("src", "main", "resources", "outputs", "sales-discounts.json");

}
