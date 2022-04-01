package com.example.menuoftheweekspringbootteamproject;

import com.example.menuoftheweekspringbootteamproject.model.Dish;

import java.util.List;

public class DishListDto {

    private List<Dish> dishList;

    public DishListDto() {
    }

    public DishListDto(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }
}
