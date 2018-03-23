package com.company.restaulator.models.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(name = "sales_meals",
        joinColumns = @JoinColumn(name = "sale_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "meal_id", referencedColumnName = "id"))
    private List<Meal> meals;

    @Column(nullable = false)
    @DecimalMin("0.1")
    private BigDecimal price;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    public Sale() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}