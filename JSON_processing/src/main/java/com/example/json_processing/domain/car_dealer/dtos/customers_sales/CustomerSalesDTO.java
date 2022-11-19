package com.example.json_processing.domain.car_dealer.dtos.customers_sales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSalesDTO {

    private String fullName;

    private long boughtCars;

    private double spentMoney;

    public double getSpentMoney() {
        NumberFormat formatter = new DecimalFormat("#0000000.00");
        return Double.parseDouble(formatter.format(this.spentMoney));
    }

    public void setSpentMoney(double spentMoney) {
        NumberFormat formatter = new DecimalFormat("#0000000.00");
        this.spentMoney = Double.parseDouble(formatter.format(spentMoney));
    }

    @Override
    public String toString() {
        return "CustomerSalesDTO{" +
                "fullName='" + fullName + '\'' +
                ", boughtCars=" + boughtCars +
                ", spentMoney=" + spentMoney +
                '}';
    }
}
