package com.example.menuoftheweekspringbootteamproject;

public class ShoppingIngredientDto {

    private String shoppingIngredientName;

    private Integer shoppingQuantity;

    public ShoppingIngredientDto() {
    }

    public ShoppingIngredientDto(String shoppingIngredientName, Integer shoppingQuantity) {
        this.shoppingIngredientName = shoppingIngredientName;
        this.shoppingQuantity = shoppingQuantity;
    }

    public String getShoppingIngredientName() {
        return shoppingIngredientName;
    }

    public void setShoppingIngredientName(String shoppingIngredientName) {
        this.shoppingIngredientName = shoppingIngredientName;
    }

    public Integer getShoppingQuantity() {
        return shoppingQuantity;
    }

    public void setShoppingQuantity(Integer shoppingQuantity) {
        this.shoppingQuantity = shoppingQuantity;
    }
}
