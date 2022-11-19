package com.example.json_processing.domain.car_dealer.dtos.ordered_customers;

import com.example.json_processing.domain.car_dealer.entities.Part;
import com.example.json_processing.domain.car_dealer.entities.Sale;
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
public class CustomerOrderedDTO {
    private Long id;

    private String name;

    private LocalDateTime birthDate;

    private Boolean isYoungDriver;

    private Set<SaleToExportDTO> sales = new HashSet<>();

    @Override
    public String toString() {
        return "CustomerOrderedDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", isYoungDriver=" + isYoungDriver +
                ", sales=" + sales +
                '}';
    }

}

