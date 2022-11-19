package com.example.json_processing.services.car_dealer;

import com.example.json_processing.domain.car_dealer.dtos.sales_discount.SalesDiscountDTO;

import java.io.IOException;
import java.util.List;

public interface SaleService {

    List<SalesDiscountDTO> getAllSalesWithDiscounts() throws IOException;
}
