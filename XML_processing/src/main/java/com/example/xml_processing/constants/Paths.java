package com.example.xml_processing.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    // #1 products_shop
    public static final Path USERS_XML_PATH = Path.of("src", "main", "resources", "dbContent", "users.xml");
    public static final Path CATEGORIES_XML_PATH = Path.of("src", "main", "resources", "dbContent", "categories.xml");
    public static final Path PRODUCTS_XML_PATH = Path.of("src", "main", "resources", "dbContent", "products.xml");

    public static final Path PRODUCTS_WITH_NO_BUYERS_IN_RANGE_XML_PATH = Path.of("src", "main", "resources", "outputs", "products_in_range.xml");
    public static final Path USERS_WITH_SOLD_PRODUCTS_XML_PATH = Path.of("src", "main", "resources", "outputs", "users-sold-products.xml");
    public static final Path CATEGORIES_BY_PRODUCTS_XML_PATH = Path.of("src", "main", "resources", "outputs", "categories-by-products.xml");
    public static final Path USERS_AND_PRODUCTS_XML_PATH = Path.of("src", "main", "resources", "outputs", "users-and-products.xml");

    // #2 car_dealer
    public static final Path SUPPLIERS_XML_PATH = Path.of("src", "main", "resources", "dbContent", "suppliers.xml");
    public static final Path PARTS_XML_PATH = Path.of("src", "main", "resources", "dbContent", "parts.xml");
    public static final Path CARS_XML_PATH = Path.of("src", "main", "resources", "dbContent", "cars.xml");
    public static final Path CUSTOMERS_XML_PATH = Path.of("src", "main", "resources", "dbContent", "customers.xml");

    public static final Path ORDERED_CUSTOMERS_XML_PATH = Path.of("src", "main", "resources", "outputs", "ordered-customers.xml");
    public static final Path TOYOTA_CARS_XML_PATH = Path.of("src", "main", "resources", "outputs", "toyota-cars.xml");
    public static final Path LOCAL_SUPPLIER_XML_PATH = Path.of("src", "main", "resources", "outputs", "local-suppliers.xml");
    public static final Path CARS_AND_PARTS_XML_PATH = Path.of("src", "main", "resources", "outputs", "cars-and-parts.xml");
    public static final Path CUSTOMERS_TOTAL_SALES_XML_PATH = Path.of("src", "main", "resources", "outputs", "customers-total-sales.xml");
    public static final Path SALES_DISCOUNTS_XML_PATH = Path.of("src", "main", "resources", "outputs", "sales-discounts.xml");
}
