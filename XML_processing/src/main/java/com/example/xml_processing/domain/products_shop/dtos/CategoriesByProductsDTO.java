package com.example.xml_processing.domain.products_shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductsDTO {

    @XmlAttribute
    private String name;

    @XmlElement(name = "products-count")
    private Long productCount;

    @XmlElement(name = "average-price")
    private Double averagePrice;

    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;
}
