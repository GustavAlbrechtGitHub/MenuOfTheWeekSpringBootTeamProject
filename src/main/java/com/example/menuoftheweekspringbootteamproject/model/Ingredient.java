package com.example.menuoftheweekspringbootteamproject.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "ingridient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private boolean isVegetarian;


    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
    ArrayList<Dish> dishes = new ArrayList<>();

    public Ingredient() {
    }

    public Ingredient(Integer id, String name, boolean isVegetarian, ArrayList<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.dishes = dishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}