package com.company.restaulator.services;

import com.company.restaulator.models.dtos.SupplierDTO;
import com.company.restaulator.models.entities.Supplier;
import com.company.restaulator.repositories.SupplierRepository;
import com.company.restaulator.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepo;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    @Override
    public void save(SupplierDTO supplierDTO) {
        Supplier supplier = DTOConverter.convert(supplierDTO, Supplier.class);
        this.supplierRepo.save(supplier);
    }

    @Override
    public SupplierDTO findByName(String name) {
        Supplier supplier = this.supplierRepo.findByName(name);
        if(supplier == null) {
            return null;
        }
        return DTOConverter.convert(supplier, SupplierDTO.class);
    }

    @Override
    public List<SupplierDTO> findAll() {
        List<Supplier> suppliers = this.supplierRepo.findAll();
        List<SupplierDTO> resultSuppliers = suppliers.stream()
                .map(s -> DTOConverter.convert(s, SupplierDTO.class))
                .collect(Collectors.toList());
        return resultSuppliers;
    }
}
