package com.example.json_processing.services.car_dealer;

import com.example.json_processing.domain.car_dealer.dtos.local_suppliers.SupplierLocalDTO;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    List<SupplierLocalDTO> getSuppliersByIsImporterIsFalse() throws IOException;
}
