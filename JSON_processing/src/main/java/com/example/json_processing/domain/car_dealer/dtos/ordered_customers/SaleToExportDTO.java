package com.example.json_processing.domain.car_dealer.dtos.ordered_customers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleToExportDTO {

    private Double discount;

    private CarToExportDTO car;

    @Override
    public String toString() {
        return "SaleToExportDTO{" +
                "discount=" + discount +
                ", car=" + car +
                '}';
    }
}
