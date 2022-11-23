package com.example.xml_processing.domain.products_shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInRangeWithNoBuyerDTO {

    @XmlElement(name = "product")
    private List<ProductInRangeWithNoBuyerDTO> products;
}
