package com.example.xml_processing.domain.car_dealer.dtos.cars_and_parts;

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
@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsForCarDTO {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;
}
