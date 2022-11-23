package com.example.xml_processing.domain.car_dealer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(name = "travelled_distance", nullable = false)
    private Long travelledDistance;

    @OneToMany(mappedBy = "car", targetEntity = Sale.class, fetch = FetchType.EAGER)
    private Set<Sale> sale = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Part> parts = new HashSet<>();
}
