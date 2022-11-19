package com.example.json_processing.constants;

import com.example.json_processing.domain.car_dealer.dtos.ordered_customers.CustomerOrderedDTO;
import com.example.json_processing.domain.car_dealer.entities.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public enum Utils {
    ;
    public static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void writeJsonIntoFiles(List<?> objects, Path filePath) throws IOException {
        final FileWriter fileWriter = new FileWriter(filePath.toFile());

        GSON.toJson(objects, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }
    public static void writeJsonIntoFiles(Object object, Path filePath) throws IOException {
        final FileWriter fileWriter = new FileWriter(filePath.toFile());

        GSON.toJson(object, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    //NO NEEDED
    private void customerToOrderedCustomersDto(ModelMapper mapper) {
        Converter<LocalDateTime, String> toDateToString =
                ctx -> DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ctx.getSource());

        mapper.createTypeMap(Customer.class, CustomerOrderedDTO.class)
                .addMapping(Customer::getBirthDate, CustomerOrderedDTO::setBirthDate);
    }
}
