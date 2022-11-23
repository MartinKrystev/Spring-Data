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
@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerWrapperDTO {

    @XmlElement(name = "customer")
    private List<CustomersImportDTO> customers;

    public CustomerWrapperDTO(List<CustomersImportDTO> customers) {
        this.customers = customers;
    }

}
