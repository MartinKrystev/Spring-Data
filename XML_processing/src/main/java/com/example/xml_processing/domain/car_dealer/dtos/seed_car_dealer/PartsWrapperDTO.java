package com.example.xml_processing.domain.car_dealer.dtos.seed_car_dealer;

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
@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsWrapperDTO {

    @XmlElement(name = "part")
    private List<PartsImportDTO> parts;

    public PartsWrapperDTO(List<PartsImportDTO> parts) {
        this.parts = parts;
    }
}
