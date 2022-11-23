package com.example.xml_processing.domain.car_dealer.dtos.cars_and_parts;

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
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsAndPartsWrapperDTO {

    @XmlElement(name = "car")
    private List<CarToMakeDTO> car;

    public CarsAndPartsWrapperDTO(List<CarToMakeDTO> carsDto) {
        this.car = carsDto;
    }
}
