package com.company.restaulator.services;

import com.company.restaulator.models.dtos.SupplierDTO;

import java.util.List;

public interface SupplierService {
    void save(SupplierDTO supplierDTO);
    SupplierDTO findByName(String name);
    List<SupplierDTO> findAll();
}