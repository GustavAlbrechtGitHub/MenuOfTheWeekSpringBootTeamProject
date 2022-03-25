package com.example.menuoftheweekspringbootteamproject.model;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ingredients_ID")
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
