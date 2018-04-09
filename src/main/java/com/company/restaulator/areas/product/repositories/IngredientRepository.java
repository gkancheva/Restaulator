package com.company.restaulator.areas.product.repositories;

import com.company.restaulator.areas.product.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("SELECT i FROM Ingredient AS i WHERE i.ordered = TRUE")
    List<Ingredient> findAllDelivered();

    Ingredient findById(long id);
}