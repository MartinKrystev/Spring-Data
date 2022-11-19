package com.example.json_processing.domain.car_dealer.dtos.cars_parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarToMakeDTO {
    private String make;

    private String model;

    private Long travelledDistance;

    @Override
    public String toString() {
        return "CarToMakeDTO{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", travelledDistance=" + travelledDistance +
                '}';
    }
}
