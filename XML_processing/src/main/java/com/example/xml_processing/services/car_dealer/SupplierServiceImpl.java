package com.example.xml_processing.services.car_dealer;

import com.example.xml_processing.domain.car_dealer.dtos.local_suppliers.SupplierLocalDTO;
import com.example.xml_processing.domain.car_dealer.dtos.local_suppliers.SupplierLocalForSizeDTO;
import com.example.xml_processing.domain.car_dealer.dtos.local_suppliers.SupplierWrapperDTO;
import com.example.xml_processing.repositories.car_dealer.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.xml_processing.constants.Paths.LOCAL_SUPPLIER_XML_PATH;
import static com.example.xml_processing.constants.Utils.writeXmlIntoFile;

@Service
public class SupplierServiceImpl implements SupplierService{

    private final SupplierRepository supplierRepository;
    private final ModelMapper mapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository,
                               ModelMapper mapper) {
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
    }


    @Override
    public List<SupplierLocalDTO> getSuppliersByIsImporterIsFalse() throws IOException, JAXBException {
        List<SupplierLocalDTO> suppliers = this.supplierRepository.getSuppliersByIsImporterIsFalse().orElseThrow(NoSuchElementException::new)
                .stream()
                .map(supplier -> mapper.map(supplier, SupplierLocalForSizeDTO.class))
                .map(SupplierLocalForSizeDTO::toSupplierLocalDTO)
                .toList();

        SupplierWrapperDTO supplierWrapperDTO = new SupplierWrapperDTO(suppliers);

        writeXmlIntoFile(supplierWrapperDTO,LOCAL_SUPPLIER_XML_PATH);
        return suppliers;
    }
}
