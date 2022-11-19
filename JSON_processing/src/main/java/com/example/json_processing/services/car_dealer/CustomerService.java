package com.example.json_processing.services.car_dealer;

import com.example.json_processing.domain.car_dealer.dtos.customers_sales.CustomerSalesDTO;
import com.example.json_processing.domain.car_dealer.dtos.ordered_customers.CustomerOrderedDTO;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    List<CustomerOrderedDTO> getAllCustomersOrderedByBirthDate() throws IOException;

    List<CustomerSalesDTO> getAllCustomersWithSales() throws IOException;
}
