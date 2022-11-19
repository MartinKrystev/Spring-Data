package com.example.json_processing.services.car_dealer;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CarDealerSeedService {

    void seedSuppliers() throws IOException;

    void seedParts() throws IOException;

    void seedCars() throws IOException;

    void seedCustomers() throws IOException;

    void seedSales();

    default void seedAll() throws IOException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();
    }
}
