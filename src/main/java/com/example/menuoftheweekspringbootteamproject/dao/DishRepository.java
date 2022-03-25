package com.example.menuoftheweekspringbootteamproject.dao;

import com.example.menuoftheweekspringbootteamproject.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DishRepository extends CrudRepository<Dish, Integer> {

    @Query(value = "SELECT d FROM Dish d WHERE d.name like %:keyword%")
    List<Dish> findByKeyword(@Param("keyword") String keyword);
}
