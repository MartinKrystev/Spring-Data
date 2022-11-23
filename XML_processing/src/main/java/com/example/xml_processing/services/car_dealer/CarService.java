package com.example.xml_processing.services.car_dealer;

import com.example.xml_processing.domain.car_dealer.dtos.cars_and_parts.CarToMakeDTO;
import com.example.xml_processing.domain.car_dealer.dtos.toyota_cars.CarToyotasDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CarService {

    List<CarToyotasDTO> getAllToyotaCars() throws IOException, JAXBException;

    List<CarToMakeDTO> getAllByIdGreaterThan(Long zero) throws IOException, JAXBException;
}
