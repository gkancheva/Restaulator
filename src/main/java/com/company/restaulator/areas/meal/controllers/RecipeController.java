package com.company.restaulator.areas.meal.controllers;

import com.company.restaulator.areas.meal.dtos.RecipeDTO;
import com.company.restaulator.areas.meal.services.RecipeService;
import com.company.restaulator.areas.product.dtos.IngredientDTO;
import com.company.restaulator.areas.product.services.IngredientService;
import com.company.restaulator.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController extends BaseController {

    private static final String RECIPES_VIEW_ALL = "meals/recipes";
    private static final String RECIPES_VIEW_ALL_REDIRECT = "recipes";
    private static final String RECIPE_ADD_VIEW = "meals/add-recipe";
    private static final String RECIPE_ADD_VIEW_QUANTITIES = "meals/add-recipe-quantities";
    private static final String RECIPE_ADD_VIEW_QTIES_REDIRECT = "recipes/add-recipe-quantities";
    private static final String RECIPES_KEY = "recipes";
    private static final String RECIPE_KEY = "recipe";
    private static final String INGREDIENTS_KEY = "ingredients";

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public RecipeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ModelAndView recipes() {
        List<RecipeDTO> recipes = this.recipeService.findAll();
        return this.view(RECIPES_VIEW_ALL, RECIPES_KEY, recipes);
    }

    @GetMapping("/add")
    public ModelAndView add(@ModelAttribute(RECIPE_KEY) RecipeDTO recipe) {
        List<IngredientDTO> ingredients = this.ingredientService.findAll();
        return this.view(RECIPE_ADD_VIEW, INGREDIENTS_KEY, ingredients);
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute(RECIPE_KEY) RecipeDTO recipe, BindingResult br, RedirectAttributes ra) {
        if(br.hasErrors()) {
            return this.view(RECIPE_ADD_VIEW, INGREDIENTS_KEY, this.ingredientService.findAll());
        }
        return this.redirectWithAttr(RECIPE_ADD_VIEW_QTIES_REDIRECT, RECIPE_KEY, recipe, ra);
    }

    @GetMapping("/add-recipe-quantities")
    public ModelAndView saveRecipe(@ModelAttribute(RECIPE_KEY) RecipeDTO recipe) {
        return this.view(RECIPE_ADD_VIEW_QUANTITIES, RECIPE_KEY, recipe);
    }

    @PostMapping("/add-recipe-quantities")
    public ModelAndView saveRecipe(@Valid @ModelAttribute(RECIPE_KEY) RecipeDTO recipe, BindingResult br) {
        if(br.hasErrors()) {
            return this.view(RECIPE_ADD_VIEW_QUANTITIES, RECIPE_KEY, recipe);
        }
        this.recipeService.save(recipe);
        return this.redirect(RECIPES_VIEW_ALL_REDIRECT);
    }

    // TODO: 4/5/2018 Edit method
}
