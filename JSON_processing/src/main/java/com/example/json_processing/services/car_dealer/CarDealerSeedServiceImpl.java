package com.example.json_processing.services.car_dealer;

import com.example.json_processing.domain.car_dealer.dtos.CarImportDTO;
import com.example.json_processing.domain.car_dealer.dtos.CustomerDTO;
import com.example.json_processing.domain.car_dealer.dtos.PartImportDTO;
import com.example.json_processing.domain.car_dealer.dtos.SupplierImportDTO;
import com.example.json_processing.domain.car_dealer.entities.*;
import com.example.json_processing.repositories.car_dealer.*;
import com.google.gson.*;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.json_processing.constants.Paths.*;
import static com.example.json_processing.constants.Utils.GSON;
import static com.example.json_processing.constants.Utils.MODEL_MAPPER;

@Service
public class CarDealerSeedServiceImpl implements CarDealerSeedService {

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final SaleRepository saleRepository;

    public CarDealerSeedServiceImpl(SupplierRepository supplierRepository,
                                    PartRepository partRepository,
                                    CustomerRepository customerRepository,
                                    CarRepository carRepository,
                                    SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.saleRepository = saleRepository;
    }


    @Override
    public void seedSuppliers() throws IOException {
        if (this.supplierRepository.count() == 0) {
            final FileReader fileReader = new FileReader(SUPPLIERS_JSON_PATH.toFile());

            List<Supplier> suppliers = Arrays.stream(GSON.fromJson(fileReader, SupplierImportDTO[].class))
                    .map(SupplierImportDTO -> MODEL_MAPPER.map(SupplierImportDTO, Supplier.class))
                    .collect(Collectors.toList());

            this.supplierRepository.saveAllAndFlush(suppliers);
            fileReader.close();
        }
    }

    @Override
    public void seedParts() throws IOException {
        if (this.partRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PARTS_JSON_PATH.toFile());

            List<Part> parts = Arrays.stream(GSON.fromJson(fileReader, PartImportDTO[].class))
                    .map(partImportDTO -> MODEL_MAPPER.map(partImportDTO, Part.class))
                    .map(this::setRandomSupplier)
                    .collect(Collectors.toList());

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
    public void seedCars() throws IOException {
        if (this.carRepository.count() == 0) {
            final FileReader fileReader = new FileReader(CARS_JSON_PATH.toFile());

            List<Car> cars = Arrays.stream(GSON.fromJson(fileReader, CarImportDTO[].class))
                    .map(carImportDTO -> MODEL_MAPPER.map(carImportDTO, Car.class))
                    .map(this::setRandomParts)
                    .collect(Collectors.toList());

            this.carRepository.saveAllAndFlush(cars);
            fileReader.close();
        }
    }

    private Car setRandomParts(Car car) {
        int countParts = ThreadLocalRandom.current().nextInt(3, 6);
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
    public void seedCustomers() throws IOException {
        if (this.customerRepository.count() == 0) {
            final FileReader fileReader = new FileReader(CUSTOMERS_JSON_PATH.toFile());

            // tricky LocalDateTime parse
            Gson testGson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                @Override
                public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

                    String dateString = json.getAsJsonPrimitive().getAsString();
                    LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
                    return dateTime;
                }
            }).create();

            List<Customer> customers = Arrays.stream(testGson.fromJson(fileReader, CustomerDTO[].class))
                    .map(customerDTO -> MODEL_MAPPER.map(customerDTO, Customer.class))
                    .collect(Collectors.toList());

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
