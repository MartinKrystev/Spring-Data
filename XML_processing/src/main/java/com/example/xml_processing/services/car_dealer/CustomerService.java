package com.example.xml_processing.services.car_dealer;

import com.example.xml_processing.domain.car_dealer.dtos.customers_sales.CustomerSalesDTO;
import com.example.xml_processing.domain.car_dealer.dtos.ordered_customers.CustomerDTO;
import com.example.xml_processing.domain.car_dealer.entities.Customer;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomersOrderedByBirthDate() throws IOException, JAXBException;

    List<CustomerSalesDTO> getAllCustomersWithSales() throws IOException, JAXBException;
}
