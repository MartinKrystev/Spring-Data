package com.example.xml_processing.domain.car_dealer.dtos.local_suppliers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierLocalDTO {

    @XmlAttribute
    private Long id;

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "parts-count")
    private Integer parts;

    public SupplierLocalDTO(List<SupplierLocalForSizeDTO> supplierLocalForSizeDTOS) {
        this.parts = supplierLocalForSizeDTOS.size();
    }

}
