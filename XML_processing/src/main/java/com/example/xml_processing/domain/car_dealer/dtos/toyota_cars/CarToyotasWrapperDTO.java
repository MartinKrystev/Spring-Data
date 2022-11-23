package com.example.xml_processing.domain.car_dealer.dtos.toyota_cars;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarToyotasWrapperDTO {

    @XmlElement(name = "car")
    private List<CarToyotasDTO> cars;

    public CarToyotasWrapperDTO(List<CarToyotasDTO> cars) {
        this.cars = cars;
    }
}
