package com.example.json_processing.services.car_dealer;

import com.example.json_processing.domain.car_dealer.dtos.sales_discount.SalesDiscountDTO;
import com.example.json_processing.repositories.car_dealer.SaleRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.json_processing.constants.Paths.SALES_DISCOUNTS_JSON_PATH;
import static com.example.json_processing.constants.Utils.writeJsonIntoFiles;

@Service
public class SaleServiceImpl implements SaleService{
    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    @Override
    public List<SalesDiscountDTO> getAllSalesWithDiscounts() throws IOException {
        List<SalesDiscountDTO> salesWithDiscounts = this.saleRepository.getAllSalesWithDiscounts()
                .orElseThrow(NoSuchElementException::new)
                .stream().toList();

        writeJsonIntoFiles(salesWithDiscounts, SALES_DISCOUNTS_JSON_PATH);
        return salesWithDiscounts;
    }
}
