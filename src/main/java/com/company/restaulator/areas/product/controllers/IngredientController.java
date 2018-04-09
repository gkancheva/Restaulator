package com.company.restaulator.areas.product.controllers;

import com.company.restaulator.areas.product.dtos.IngredientDTO;
import com.company.restaulator.areas.product.services.IngredientService;
import com.company.restaulator.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IngredientController extends BaseController {

    private static final String INGREDIENTS_KEY = "ingredients";
    private static final String INGREDIENT_VIEW = "products/ingredients";

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/inventory")
    public ModelAndView inventory() {
        List<IngredientDTO> ingredients = this.ingredientService.findAllOrdered();
        return this.view(INGREDIENT_VIEW, INGREDIENTS_KEY, ingredients);
    }
}