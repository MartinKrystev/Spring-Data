package com.example.xml_processing.domain.car_dealer.dtos.local_suppliers;

import com.example.xml_processing.domain.car_dealer.entities.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierLocalForSizeDTO {

    private Long id;
    private String name;
    private Set<Part> parts;

    public SupplierLocalDTO toSupplierLocalDTO(){
        return new SupplierLocalDTO(id, name, parts.size());
    }
}
