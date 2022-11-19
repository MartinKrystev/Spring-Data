package com.example.json_processing.domain.car_dealer.dtos.ordered_customers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarToExportDTO {
    private String make;

    private String model;

    private Long travelledDistance;

    @Override
    public String toString() {
        return "CarToExportDTO{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", travelledDistance=" + travelledDistance +
                '}';
    }
}
