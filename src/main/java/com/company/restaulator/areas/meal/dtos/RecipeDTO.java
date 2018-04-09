package com.company.restaulator.areas.meal.dtos;

import com.company.restaulator.areas.product.dtos.IngredientDTO;

import java.math.BigDecimal;
import java.util.List;

public class RecipeDTO {
    // TODO: 4/4/2018 Validate fields
    private long id;
    private String name;
    private List<IngredientDTO> ingredients;
    private double quantity;
    private BigDecimal cost;

    public RecipeDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}