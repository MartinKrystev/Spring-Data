package com.example.json_processing.domain.car_dealer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //•	A car has many parts and one part can be placed in many cars
    //•	One supplier can supply many parts and each part can be delivered by only one supplier
    //•	In one sale, only one car can be sold
    //•	Each sale has one customer and a customer can buy many cars
}
