package com.example.menuoftheweekspringbootteamproject.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 40, nullable = false)
    private String description;

    private int likes;

    private boolean isOrdered;

    private int orderCounter = 0;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "dishes", fetch = FetchType.EAGER)
    List<Ingredient> ingredients = new ArrayList<>();

    public Dish() {

    }



    public Integer getOrderCounter() {
        return orderCounter;
    }

    public void setOrderCounter(Integer addCount) {
        this.orderCounter = orderCounter + addCount;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }
}
