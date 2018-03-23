package com.company.restaulator.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "weekly_menus")
public class WeeklyMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(name = "weekly_menu_meals",
            joinColumns = @JoinColumn(name = "weekly_menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id", referencedColumnName = "id"))
    private List<Meal> meals;

    public WeeklyMenu() {
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
}