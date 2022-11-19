package com.example.json_processing.domain.car_dealer.dtos.cars_parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarsAndPartsDTO {

    CarToMakeDTO car;

    private List<PartsForCarDTO> parts = new ArrayList<>();

    @Override
    public String toString() {
        return "CarsAndPartsDTO{" +
                "car=" + car +
                ", parts=" + parts +
                '}';
    }
}
