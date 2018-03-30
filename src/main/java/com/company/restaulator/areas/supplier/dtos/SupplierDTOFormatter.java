package com.company.restaulator.areas.supplier.dtos;

import com.company.restaulator.areas.supplier.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class SupplierDTOFormatter implements Formatter<SupplierDTO> {

    private final SupplierService supplierService;

    @Autowired
    public SupplierDTOFormatter(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public SupplierDTO parse(String s, Locale locale) throws ParseException {
        long id = Long.parseLong(s);
        return this.supplierService.findById(id);
    }

    @Override
    public String print(SupplierDTO role, Locale locale) {
        return (role != null ? String.valueOf(role.getId()) : "");
    }
}
