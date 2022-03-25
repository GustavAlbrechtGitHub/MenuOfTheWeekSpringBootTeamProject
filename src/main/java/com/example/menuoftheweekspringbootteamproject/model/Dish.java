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

    private String description;

    private int likes;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    public Dish() {
    }

    public Dish(String name, String description, int likes, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.likes = likes;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String descrption) {
        this.description = descrption;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int addedLike) {
        this.likes = likes + addedLike;
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
