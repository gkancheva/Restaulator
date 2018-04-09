package com.company.restaulator.areas.meal.services;

import com.company.restaulator.areas.DTOConverter;
import com.company.restaulator.areas.meal.dtos.RecipeDTO;
import com.company.restaulator.areas.meal.entities.Recipe;
import com.company.restaulator.areas.meal.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepo;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @Override
    public List<RecipeDTO> findAll() {
        List<Recipe> recipes = this.recipeRepo.findAll();
        return recipes.stream()
                .map(r -> DTOConverter.convert(r, RecipeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecipeDTO findById(long id) {
        return DTOConverter.convert(this.recipeRepo.findById(id), RecipeDTO.class);
    }

    @Override
    public void save(RecipeDTO recipe) {
        Recipe r = DTOConverter.convert(recipe, Recipe.class);
        this.recipeRepo.save(r);
    }
}