package com.example.xml_processing.domain.car_dealer.dtos.customers_sales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSalesDTO {

    @XmlAttribute(name = "full-name")
    private String fullName;

    @XmlAttribute(name = "bought-cars")
    private long boughtCars;

    @XmlAttribute(name = "spent-money")
    private double spentMoney;
}
