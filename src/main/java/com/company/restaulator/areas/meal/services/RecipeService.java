package com.company.restaulator.areas.meal.services;

import com.company.restaulator.areas.meal.dtos.RecipeDTO;

import java.util.List;

public interface RecipeService {
    List<RecipeDTO> findAll();
    RecipeDTO findById(long id);
    void save(RecipeDTO recipe);
}