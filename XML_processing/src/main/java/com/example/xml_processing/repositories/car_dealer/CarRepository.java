package com.example.xml_processing.repositories.car_dealer;

import com.example.xml_processing.domain.car_dealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "select * from db_xml.cars order by rand() limit 1", nativeQuery = true)
    Optional<Car> getRandomCarEntity();

    @Query(value = "select * from db_xml.cars where make = 'Toyota' order by model, travelled_distance desc ", nativeQuery = true)
    Optional<List<Car>> getAllToyotaCars();

    Optional<List<Car>> getAllByIdGreaterThan(Long zero);
}
