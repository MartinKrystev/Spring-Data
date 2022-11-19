package com.example.json_processing.domain.car_dealer.dtos.cars_parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartsForCarDTO {
    private String name;
    private BigDecimal price;

    @Override
    public String toString() {
        return "PartsForCarDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
