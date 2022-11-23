package com.example.xml_processing.domain.car_dealer.dtos.sales_discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesDiscountDTO {

    @XmlElement(name = "car")
    private CarSimpleDTO car;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement(name = "discount")
    private Double discount;

    @XmlElement(name = "price")
    private BigDecimal price;

    @XmlElement(name = "price-with-discount")
    private BigDecimal priceWithDiscount;

    public SalesDiscountDTO(String make, String model, Long travelledDistance,
                            String customerName, Double discount, BigDecimal price) {
        this.car = new CarSimpleDTO(make, model, travelledDistance);

        this.customerName = customerName;
        this.discount = discount;
        this.price = price;
        this.priceWithDiscount = price.subtract(price.multiply(BigDecimal.valueOf(discount)));
    }
}