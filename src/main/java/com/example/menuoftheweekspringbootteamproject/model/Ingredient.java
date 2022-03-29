package com.example.menuoftheweekspringbootteamproject.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 40, nullable = false)
    private String ingredientName;



    @ManyToMany( cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    List<Dish> dishes;

    public Ingredient() { this.dishes = new ArrayList<>();
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Ingredient(Integer id, String ingredientName, List<Dish> dishes) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.dishes = dishes;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String name) {
        this.ingredientName = name;
    }


    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}