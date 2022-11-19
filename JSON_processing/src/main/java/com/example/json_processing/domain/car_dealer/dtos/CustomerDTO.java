package com.example.json_processing.domain.car_dealer.dtos;

import com.example.json_processing.domain.car_dealer.dtos.ordered_customers.CustomerOrderedDTO;
import com.example.json_processing.domain.car_dealer.dtos.ordered_customers.SaleToExportDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private String name;
    private LocalDateTime birthDate;
    private Boolean isYoungDriver;
    private Set<SaleToExportDTO> sales = new HashSet<>();
    public CustomerOrderedDTO toCustomerOrderedDTO() {
        return new CustomerOrderedDTO(id, name, birthDate, isYoungDriver, sales);
    }
}
