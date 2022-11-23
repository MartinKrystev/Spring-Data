package com.example.xml_processing.services.car_dealer;

import com.example.xml_processing.domain.car_dealer.dtos.sales_discount.SalesDiscountDTO;
import com.example.xml_processing.domain.car_dealer.dtos.sales_discount.SalesDiscountWrapperDTO;
import com.example.xml_processing.repositories.car_dealer.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.xml_processing.constants.Paths.SALES_DISCOUNTS_XML_PATH;
import static com.example.xml_processing.constants.Utils.writeXmlIntoFile;

@Service
public class SaleServiceImpl implements SaleService{
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<SalesDiscountDTO> getAllSalesWithDiscounts() throws IOException, JAXBException {
        List<SalesDiscountDTO> salesWithDiscounts = this.saleRepository.getAllSalesWithDiscounts()
                .orElseThrow(NoSuchElementException::new)
                .stream().toList();

        SalesDiscountWrapperDTO salesDiscountWrapperDTO = new SalesDiscountWrapperDTO(salesWithDiscounts);

        writeXmlIntoFile(salesDiscountWrapperDTO, SALES_DISCOUNTS_XML_PATH);
        return salesWithDiscounts;
    }
}
