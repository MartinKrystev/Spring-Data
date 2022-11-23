package com.example.xml_processing.domain.car_dealer.entities;

import com.example.xml_processing.config.LocalDateTimeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver", nullable = false)
    private Boolean isYoungDriver;

    @OneToMany(mappedBy = "customer", targetEntity = Sale.class, fetch = FetchType.EAGER)
    private Set<Sale> sales = new HashSet<>();

}
