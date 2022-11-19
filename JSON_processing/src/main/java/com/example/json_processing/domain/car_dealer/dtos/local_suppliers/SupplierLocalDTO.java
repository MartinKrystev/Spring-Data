package com.example.json_processing.domain.car_dealer.dtos.local_suppliers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierLocalDTO {

    private Long id;
    private String name;
    private Integer parts;

    public SupplierLocalDTO(List<SupplierLocalForSizeDTO> supplierLocalForSizeDTOS) {
        this.parts = supplierLocalForSizeDTOS.size();
    }

}
