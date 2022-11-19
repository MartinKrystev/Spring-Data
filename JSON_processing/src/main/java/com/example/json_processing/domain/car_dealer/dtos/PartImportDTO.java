package com.example.json_processing.domain.car_dealer.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartImportDTO {

    private String name;

    private BigDecimal price;

    private Integer quantity;
}
