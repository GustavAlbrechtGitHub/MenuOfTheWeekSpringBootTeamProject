package com.example.menuoftheweekspringbootteamproject.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer IngredientId;

    @Column(length = 40, nullable = false)
    private String ingredientName;



    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Dish> dishes = new ArrayList<>();

    public Ingredient() {
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Ingredient(Integer IngredientId, String ingredientName, List<Dish> dishes) {
        this.IngredientId = IngredientId;
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

    public Integer getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(Integer id) {
        this.IngredientId = id;
    }
}