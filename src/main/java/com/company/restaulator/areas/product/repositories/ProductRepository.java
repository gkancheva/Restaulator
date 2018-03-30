package com.company.restaulator.areas.product.repositories;

import com.company.restaulator.areas.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product AS p WHERE p.supplier.id = ?1")
    List<Product> findAllBySupplier(long supplierId);

    Product findById(long id);
}