package com.example.json_processing.domain.car_dealer.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierImportDTO {

    private String name;

    private Boolean isImporter;
}
