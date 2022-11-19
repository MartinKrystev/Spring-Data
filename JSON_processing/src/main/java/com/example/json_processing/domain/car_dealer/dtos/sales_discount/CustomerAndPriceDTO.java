package com.example.json_processing.domain.car_dealer.dtos.sales_discount;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAndPriceDTO {
    private String customerName;
    @SerializedName("Discount")
    private Double discount;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;

    public CustomerAndPriceDTO(String customerName, Double discount, BigDecimal price) {
        this.customerName = customerName;
        this.discount = discount;
        this.price = price;
        this.priceWithDiscount =  price.subtract(price.multiply(BigDecimal.valueOf(discount)));
    }

}
