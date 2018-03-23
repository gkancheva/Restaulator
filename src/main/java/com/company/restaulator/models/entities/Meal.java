package com.company.restaulator.models.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Column(name = "total_nb_portions")
    private int totalNbPortions;

    @Column(name = "sale_price")
    @DecimalMin("0.1")
    private BigDecimal salePrice;

    public Meal() {
    }

    public Meal(Recipe recipe, int totalNbPortions, @DecimalMin("0.1") BigDecimal salePrice) {
        this.recipe = recipe;
        this.totalNbPortions = totalNbPortions;
        this.salePrice = salePrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getTotalNbPortions() {
        return totalNbPortions;
    }

    public void setTotalNbPortions(int totalNbPortions) {
        this.totalNbPortions = totalNbPortions;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
