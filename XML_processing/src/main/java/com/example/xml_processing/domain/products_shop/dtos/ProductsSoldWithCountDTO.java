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
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldWithCountDTO {

    @XmlAttribute
    private Integer count;

    @XmlElement(name = "product")
    private List<ProductBasicDTO> products;
    public ProductsSoldWithCountDTO(List<ProductBasicDTO> products) {
        this.products = products;
        this.count = products.size();
    }
}
