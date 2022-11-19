package com.example.json_processing.services.car_dealer;

import com.example.json_processing.domain.car_dealer.dtos.local_suppliers.SupplierLocalDTO;
import com.example.json_processing.domain.car_dealer.dtos.local_suppliers.SupplierLocalForSizeDTO;
import com.example.json_processing.repositories.car_dealer.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.json_processing.constants.Paths.LOCAL_SUPPLIER_JSON_PATH;
import static com.example.json_processing.constants.Utils.MODEL_MAPPER;
import static com.example.json_processing.constants.Utils.writeJsonIntoFiles;

@Service
public class SupplierServiceImpl implements SupplierService{
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    @Override
    public List<SupplierLocalDTO> getSuppliersByIsImporterIsFalse() throws IOException {
        List<SupplierLocalDTO> suppliers = this.supplierRepository.getSuppliersByIsImporterIsFalse().orElseThrow(NoSuchElementException::new)
                .stream()
                .map(supplier -> MODEL_MAPPER.map(supplier, SupplierLocalForSizeDTO.class))
                .map(SupplierLocalForSizeDTO::toSupplierLocalDTO)
                .toList();

        writeJsonIntoFiles(suppliers,LOCAL_SUPPLIER_JSON_PATH);

        return suppliers;
    }
}
