package com.example.json_processing.domain.car_dealer.dtos.sales_discount;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SalesDiscountDTO {

    private CarSimpleDTO car;

    private CustomerAndPriceDTO customer;

    public SalesDiscountDTO(String make, String model, Long travelledDistance,
                            String customerName, Double discount, BigDecimal price) {

        this.car = new CarSimpleDTO(make, model, travelledDistance);
        this.customer = new CustomerAndPriceDTO(customerName, discount / 100, price);
    }

}
