package com.example.xml_processing.domain.car_dealer.dtos.seed_car_dealer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierWrapperDTO {

    @XmlElement(name = "supplier")
    private List<SupplierImportDTO> suppliers;

    public SupplierWrapperDTO(List<SupplierImportDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
