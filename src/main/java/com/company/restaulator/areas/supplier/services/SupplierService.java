package com.company.restaulator.areas.supplier.services;

import com.company.restaulator.areas.supplier.dtos.SupplierDTO;

import java.util.List;

public interface SupplierService {
    void save(SupplierDTO supplierDTO);
    SupplierDTO findById(long id);
    SupplierDTO findByName(String name);
    List<SupplierDTO> findAll();
}