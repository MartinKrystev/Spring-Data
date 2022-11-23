package com.example.xml_processing.services.car_dealer;

import com.example.xml_processing.domain.car_dealer.dtos.customers_sales.CustomerSalesDTO;
import com.example.xml_processing.domain.car_dealer.dtos.customers_sales.CustomersSalesWrapperDTO;
import com.example.xml_processing.domain.car_dealer.dtos.ordered_customers.CustomerDTO;
import com.example.xml_processing.domain.car_dealer.dtos.ordered_customers.CustomersWrapperDTO;
import com.example.xml_processing.repositories.car_dealer.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.xml_processing.constants.Paths.CUSTOMERS_TOTAL_SALES_XML_PATH;
import static com.example.xml_processing.constants.Paths.ORDERED_CUSTOMERS_XML_PATH;
import static com.example.xml_processing.constants.Utils.writeXmlIntoFile;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }


    @Override
    public List<CustomerDTO> getAllCustomersOrderedByBirthDate() throws IOException, JAXBException {

        List<CustomerDTO> customerDto = this.customerRepository.getAllCustomersOrderedByBirthDate()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(customer -> mapper.map(customer, CustomerDTO.class))
                .toList();

        CustomersWrapperDTO customersWrapperDTO = new CustomersWrapperDTO(customerDto);

        writeXmlIntoFile(customersWrapperDTO, ORDERED_CUSTOMERS_XML_PATH);
        return customerDto;
    }

    @Override
    public List<CustomerSalesDTO> getAllCustomersWithSales() throws IOException, JAXBException {
        List<CustomerSalesDTO> customers = this.customerRepository.getAllCustomersWithSales()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(customer -> mapper.map(customer, CustomerSalesDTO.class))
                .peek(x -> {
                    DecimalFormat df = new DecimalFormat("0.00");
                    x.setSpentMoney(Double.parseDouble(df.format(x.getSpentMoney())));
                })
                .toList();

        CustomersSalesWrapperDTO customersSalesWrapperDTO = new CustomersSalesWrapperDTO(customers);

//        customers.forEach(System.out::println);
        writeXmlIntoFile(customersSalesWrapperDTO, CUSTOMERS_TOTAL_SALES_XML_PATH);
        return customers;
    }
}
