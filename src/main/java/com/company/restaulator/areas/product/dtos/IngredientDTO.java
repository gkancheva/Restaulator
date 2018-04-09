package com.company.restaulator.areas.product.dtos;

import java.util.Date;

public class IngredientDTO {

    private long id;
    private ProductDTO product;
    private int quantity;
    private Date expirationDate;
    private boolean ordered;

    public IngredientDTO() {
    }

    public IngredientDTO(ProductDTO product) {
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFormattedQuantity() {
        String suffix = this.getProduct().isQuantityInKg() ? " kg" : " piece(s)";
        return this.getQuantity() + suffix;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
}
