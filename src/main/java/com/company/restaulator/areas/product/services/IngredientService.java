package com.company.restaulator.areas.product.services;

import com.company.restaulator.areas.product.dtos.IngredientDTO;

import java.util.List;

public interface IngredientService {
    List<IngredientDTO> findAll();
    List<IngredientDTO> findAllOrdered();
    List<IngredientDTO> findAllIngredientsForOrderBySupplierId(long supplierId);
    void save(IngredientDTO ingredientDTO);
    IngredientDTO findById(long id);
}