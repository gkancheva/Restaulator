package com.company.restaulator.areas.meal.repositories;

import com.company.restaulator.areas.meal.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findById(long id);
}