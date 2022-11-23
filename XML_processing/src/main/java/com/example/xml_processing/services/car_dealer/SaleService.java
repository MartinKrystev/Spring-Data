package com.example.xml_processing.services.car_dealer;

import com.example.xml_processing.domain.car_dealer.dtos.sales_discount.SalesDiscountDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface SaleService {

    List<SalesDiscountDTO> getAllSalesWithDiscounts() throws IOException, JAXBException;
}
