package com.company.restaulator.areas.product.services;

import com.company.restaulator.areas.product.dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllBySupplier(long supplierId);
    void save(ProductDTO product);
    ProductDTO findById(long id);
    void delete(ProductDTO product);
}