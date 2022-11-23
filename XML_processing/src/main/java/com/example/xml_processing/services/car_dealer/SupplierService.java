package com.example.xml_processing.services.car_dealer;

import com.example.xml_processing.domain.car_dealer.dtos.local_suppliers.SupplierLocalDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface SupplierService {

    List<SupplierLocalDTO> getSuppliersByIsImporterIsFalse() throws IOException, JAXBException;
}
