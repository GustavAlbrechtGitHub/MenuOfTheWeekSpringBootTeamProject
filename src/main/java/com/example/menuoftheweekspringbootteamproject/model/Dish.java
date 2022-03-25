package com.example.menuoftheweekspringbootteamproject.model;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private boolean isVegetarian;

    private int likes;

    private boolean isFavoured;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    public Dish() {
    }

    public Dish(String name, boolean isVegetarian, int likes, boolean isFavoured, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.likes = likes;
        this.isFavoured = isFavoured;
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isFavoured() {
        return isFavoured;
    }

    public void setFavoured(boolean favoured) {
        isFavoured = favoured;
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
