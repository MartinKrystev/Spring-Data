package com.example.json_processing.domain.car_dealer.dtos;

import com.example.json_processing.domain.car_dealer.entities.Part;
import com.example.json_processing.domain.car_dealer.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarImportDTO {
    private String make;

    private String model;

    private Long travelledDistance;

//    private Set<Sale> sale = new HashSet<>();
//
//    private Set<Part> parts = new HashSet<>();
}
