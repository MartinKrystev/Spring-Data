package com.example.xml_processing.services.car_dealer;

import com.example.xml_processing.domain.car_dealer.dtos.cars_and_parts.CarToMakeDTO;
import com.example.xml_processing.domain.car_dealer.dtos.cars_and_parts.CarsAndPartsWrapperDTO;
import com.example.xml_processing.domain.car_dealer.dtos.toyota_cars.CarToyotasDTO;
import com.example.xml_processing.domain.car_dealer.dtos.toyota_cars.CarToyotasWrapperDTO;
import com.example.xml_processing.repositories.car_dealer.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.xml_processing.constants.Paths.CARS_AND_PARTS_XML_PATH;
import static com.example.xml_processing.constants.Paths.TOYOTA_CARS_XML_PATH;
import static com.example.xml_processing.constants.Utils.writeXmlIntoFile;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          ModelMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CarToyotasDTO> getAllToyotaCars() throws IOException, JAXBException {

        List<CarToyotasDTO> carsDto = this.carRepository.getAllToyotaCars().orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> mapper.map(car, CarToyotasDTO.class))
                .toList();

        CarToyotasWrapperDTO cars = new CarToyotasWrapperDTO(carsDto);

        writeXmlIntoFile(cars,TOYOTA_CARS_XML_PATH);
        return carsDto;
    }

    @Override
    public List<CarToMakeDTO> getAllByIdGreaterThan(Long zero) throws IOException, JAXBException {
        List<CarToMakeDTO> carsDto = this.carRepository.getAllByIdGreaterThan(zero).orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> mapper.map(car, CarToMakeDTO.class))
                .collect(Collectors.toList());

        CarsAndPartsWrapperDTO carsAndPartsWrapperDTO = new CarsAndPartsWrapperDTO(carsDto);

        writeXmlIntoFile(carsAndPartsWrapperDTO,CARS_AND_PARTS_XML_PATH);
        return carsDto;
    }
}
