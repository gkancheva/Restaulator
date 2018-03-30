package com.company.restaulator.areas.product.dtos;

import com.company.restaulator.areas.supplier.entities.Supplier;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductDTO {
    private long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    private boolean quantityInKg;

    @NotNull(message = "Price cannot be empty.")
    private BigDecimal price;

    @NotEmpty(message = "Please provide producer.")
    private String producer;

    @NotNull(message = "Please select supplier.")
    private Supplier supplier;

    public ProductDTO() {
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

    public boolean isQuantityInKg() {
        return quantityInKg;
    }

    public void setQuantityInKg(boolean quantityInKg) {
        this.quantityInKg = quantityInKg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getFormattedPrice() {
        return String.format("%.2f lv/%s", this.price, this.quantityInKg ? "kg" : "piece");
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}