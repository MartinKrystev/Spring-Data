package com.example.xml_processing.services.car_dealer;

import com.example.xml_processing.domain.car_dealer.dtos.seed_car_dealer.*;
import com.example.xml_processing.domain.car_dealer.entities.*;
import com.example.xml_processing.repositories.car_dealer.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.xml_processing.constants.Paths.*;

@Service
public class CarDealerSeedServiceImpl implements CarDealerSeedService {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final PartRepository partRepository;
    private final SaleRepository saleRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper mapper;

    public CarDealerSeedServiceImpl(CarRepository carRepository,
                                    CustomerRepository customerRepository,
                                    PartRepository partRepository,
                                    SaleRepository saleRepository,
                                    SupplierRepository supplierRepository,
                                    ModelMapper mapper) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.partRepository = partRepository;
        this.saleRepository = saleRepository;
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
    }

    @Override
    public void seedSuppliers() throws IOException, JAXBException {
        if (this.supplierRepository.count() == 0) {
            final FileReader fileReader = new FileReader(SUPPLIERS_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(SupplierWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SupplierWrapperDTO supplierDto = (SupplierWrapperDTO) unmarshaller.unmarshal(fileReader);

            List<Supplier> suppliers = supplierDto.getSuppliers()
                    .stream()
                    .map(supplier -> mapper.map(supplier, Supplier.class))
                    .toList();

            this.supplierRepository.saveAllAndFlush(suppliers);
            fileReader.close();
        }
    }

    @Override
    public void seedParts() throws IOException, JAXBException {
        if (this.partRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PARTS_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(PartsWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            PartsWrapperDTO partsDto = (PartsWrapperDTO) unmarshaller.unmarshal(fileReader);

            List<Part> parts = partsDto.getParts()
                    .stream()
                    .map(part -> mapper.map(part, Part.class))
                    .map(this::setRandomSupplier)
                    .toList();

            this.partRepository.saveAllAndFlush(parts);
            fileReader.close();
        }
    }

    private Part setRandomSupplier(Part part) {
        Supplier supplier = this.supplierRepository.getRandomSupplierEntity().orElseThrow(NoSuchElementException::new);
        part.setSupplier(supplier);
        return part;
    }

    @Override
    public void seedCars() throws IOException, JAXBException {
        if (this.carRepository.count() == 0) {
            final FileReader fileReader = new FileReader(CARS_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(CarWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CarWrapperDTO carsDto = (CarWrapperDTO) unmarshaller.unmarshal(fileReader);

            List<Car> cars = carsDto.getCars()
                    .stream()
                    .map(carsImportDTO -> mapper.map(carsImportDTO, Car.class))
                    .map(this::setRandomParts)
                    .toList();

            this.carRepository.saveAllAndFlush(cars);
            fileReader.close();
        }
    }

    private Car setRandomParts(Car car) {
        int countParts = ThreadLocalRandom.current().nextInt(10, 21);
        Set<Part> parts = new HashSet<>();

        for (int i = 0; i < countParts; i++) {
            Part part = this.partRepository.getRandomPartEntity()
                    .orElseThrow(NoSuchElementException::new);
            parts.add(part);
        }
        car.setParts(parts);
        return car;
    }

    @Override
    public void seedCustomers() throws IOException, JAXBException {
        if (this.customerRepository.count() == 0) {
            final FileReader fileReader = new FileReader(CUSTOMERS_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(CustomerWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CustomerWrapperDTO customersDto = (CustomerWrapperDTO) unmarshaller.unmarshal(fileReader);

            List<Customer> customers = customersDto.getCustomers()
                    .stream()
                    .map(customersImportDTO -> mapper.map(customersImportDTO, Customer.class))
                    .toList();

            this.customerRepository.saveAllAndFlush(customers);
            fileReader.close();
        }
    }

    @Override
    public void seedSales() {
        List<Sale> sales = new ArrayList<>();
        IntStream.range(1, 100)
                .forEach(x -> {
                    Double randomDiscount = getRandomDiscount();
                    Car car = this.carRepository.getRandomCarEntity().get();
                    Customer customer = this.customerRepository.getRandomCustomerEntity().get();
                    if (customer.getIsYoungDriver()) {
                        randomDiscount += 5.0;
                    }
                    sales.add(new Sale(randomDiscount, car, customer));
                });

        this.saleRepository.saveAllAndFlush(sales);
    }

    private Double getRandomDiscount() {
        Double[] discounts = {
                0.0,
                5.0,
                10.0,
                15.0,
                20.0,
                30.0,
                40.0,
                50.0
        };
        return discounts[ThreadLocalRandom.current().nextInt(0, discounts.length)];
    }
}
