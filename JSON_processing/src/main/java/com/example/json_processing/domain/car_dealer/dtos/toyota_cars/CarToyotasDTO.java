package com.example.json_processing.domain.car_dealer.dtos.toyota_cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarToyotasDTO {

    private Long id;

    private String make;

    private String model;

    private Long travelledDistance;

    @Override
    public String toString() {
        return "CarToyotasDTO{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", travelledDistance=" + travelledDistance +
                '}';
    }
}
