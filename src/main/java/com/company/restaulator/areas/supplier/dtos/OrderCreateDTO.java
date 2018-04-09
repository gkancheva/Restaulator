package com.company.restaulator.areas.supplier.dtos;

import com.company.restaulator.areas.product.dtos.IngredientDTO;
import com.company.restaulator.areas.supplier.entities.Supplier;

import java.math.BigDecimal;
import java.util.List;

public class OrderCreateDTO {
    private SupplierDTO supplier;
    private List<IngredientDTO> ingredients;
    private BigDecimal totalPrice;

    public OrderCreateDTO() {
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}