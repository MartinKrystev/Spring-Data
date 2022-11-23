package com.example.xml_processing.domain.car_dealer.dtos.ordered_customers;

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
public class CustomersWrapperDTO {

    @XmlElement(name = "customer")
    private List<CustomerDTO> customers;

    public CustomersWrapperDTO(List<CustomerDTO> customerDTO) {
        this.customers = customerDTO;
    }

}
