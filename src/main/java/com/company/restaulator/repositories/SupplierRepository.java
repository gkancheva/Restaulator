package com.company.restaulator.repositories;

import com.company.restaulator.models.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT s FROM Supplier AS s WHERE s.name = ?1")
    Supplier findByName(String name);
}