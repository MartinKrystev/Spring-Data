package com.example.json_processing.services.car_dealer;

import com.example.json_processing.domain.car_dealer.dtos.cars_parts.CarsAndPartsDTO;
import com.example.json_processing.domain.car_dealer.dtos.toyota_cars.CarToyotasDTO;
import com.example.json_processing.repositories.car_dealer.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.json_processing.constants.Paths.CARS_AND_PARTS_JSON_PATH;
import static com.example.json_processing.constants.Paths.TOYOTA_CARS_JSON_PATH;
import static com.example.json_processing.constants.Utils.MODEL_MAPPER;
import static com.example.json_processing.constants.Utils.writeJsonIntoFiles;

@Service
public class CarServiceImpl implements CarService{
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarToyotasDTO> getAllToyotaCars() throws IOException {
        List<CarToyotasDTO> cars = this.carRepository.getAllToyotaCars().orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> MODEL_MAPPER.map(car, CarToyotasDTO.class))
                .collect(Collectors.toList());

        writeJsonIntoFiles(cars,TOYOTA_CARS_JSON_PATH);
        return cars;
    }

    @Override
    public List<CarsAndPartsDTO> getAllByIdGreaterThan(Long zero) throws IOException {
        List<CarsAndPartsDTO> cars = this.carRepository.getAllByIdGreaterThan(zero).orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> MODEL_MAPPER.map(car, CarsAndPartsDTO.class))
                .collect(Collectors.toList());

        writeJsonIntoFiles(cars,CARS_AND_PARTS_JSON_PATH);
        return cars;
    }
}
