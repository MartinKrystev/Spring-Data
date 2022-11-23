package com.example.xml_processing.domain.car_dealer.dtos.seed_car_dealer;

import com.example.xml_processing.config.LocalDateTimeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.joda.time.LocalDateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersImportDTO {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;

    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;

}
