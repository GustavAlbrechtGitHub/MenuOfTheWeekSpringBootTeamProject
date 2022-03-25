package com.example.menuoftheweekspringbootteamproject.dao;

import com.example.menuoftheweekspringbootteamproject.model.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Integer> {
}
