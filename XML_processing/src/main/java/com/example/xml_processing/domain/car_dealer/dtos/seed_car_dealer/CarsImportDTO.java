package com.example.xml_processing.domain.car_dealer.dtos.seed_car_dealer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsImportDTO {

    @XmlElement
    private String make;

    @XmlElement
    private String model;

    @XmlElement(name = "travelled-distance")
    private Long travelledDistance;
}
