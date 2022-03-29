package com.example.menuoftheweekspringbootteamproject.dao;

import com.example.menuoftheweekspringbootteamproject.model.Dish;
import com.example.menuoftheweekspringbootteamproject.model.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    @Query(value = "SELECT i FROM Ingredient i WHERE i.ingredientName like %:keyword%")
    List<Ingredient> findByKeyword(@Param("keyword") String keyword);



}
