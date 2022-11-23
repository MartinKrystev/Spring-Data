package com.example.xml_processing.domain.car_dealer.dtos.local_suppliers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierWrapperDTO {

    @XmlElement(name = "supplier")
    private List<SupplierLocalDTO> suppliers;

    public SupplierWrapperDTO(List<SupplierLocalDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
