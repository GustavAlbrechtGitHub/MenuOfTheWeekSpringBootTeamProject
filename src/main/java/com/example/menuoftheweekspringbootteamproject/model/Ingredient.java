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



    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
    ArrayList<Dish> dishes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}