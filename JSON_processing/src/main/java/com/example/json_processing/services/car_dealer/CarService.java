package com.example.json_processing.services.car_dealer;

import com.example.json_processing.domain.car_dealer.dtos.cars_parts.CarsAndPartsDTO;
import com.example.json_processing.domain.car_dealer.dtos.toyota_cars.CarToyotasDTO;
import com.example.json_processing.domain.car_dealer.entities.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {
    List<CarToyotasDTO> getAllToyotaCars() throws IOException;

    List<CarsAndPartsDTO> getAllByIdGreaterThan(Long zero) throws IOException;
}
