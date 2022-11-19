package com.example.json_processing.services.car_dealer;

import com.example.json_processing.domain.car_dealer.dtos.CustomerDTO;
import com.example.json_processing.domain.car_dealer.dtos.customers_sales.CustomerSalesDTO;
import com.example.json_processing.domain.car_dealer.dtos.ordered_customers.CustomerOrderedDTO;
import com.example.json_processing.repositories.car_dealer.CustomerRepository;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.json_processing.constants.Paths.CUSTOMERS_TOTAL_SALES_JSON_PATH;
import static com.example.json_processing.constants.Paths.ORDERED_CUSTOMERS_JSON_PATH;
import static com.example.json_processing.constants.Utils.MODEL_MAPPER;
import static com.example.json_processing.constants.Utils.writeJsonIntoFiles;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerOrderedDTO> getAllCustomersOrderedByBirthDate() throws IOException {
        class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
            private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            @Override
            public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
                return new JsonPrimitive(formatter.format(localDateTime));
            }
        }
        Gson gson5 = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .create();

        List<CustomerOrderedDTO> customers = this.customerRepository.getAllCustomersOrderedByBirthDate()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(customer -> MODEL_MAPPER.map(customer, CustomerDTO.class))
                .map(CustomerDTO::toCustomerOrderedDTO)
                .collect(Collectors.toList());

        FileWriter fileWriter = new FileWriter(ORDERED_CUSTOMERS_JSON_PATH.toFile());
        gson5.toJson(customers, fileWriter);

        fileWriter.flush();
        fileWriter.close();
        return customers;
    }

    @Override
    public List<CustomerSalesDTO> getAllCustomersWithSales() throws IOException {
        List<CustomerSalesDTO> customers = this.customerRepository.getAllCustomersWithSales()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(customer -> MODEL_MAPPER.map(customer, CustomerSalesDTO.class))
                .toList();

        writeJsonIntoFiles(customers, CUSTOMERS_TOTAL_SALES_JSON_PATH);
        return customers;
    }
}
