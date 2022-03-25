package com.example.menuoftheweekspringbootteamproject.controller;


import com.example.menuoftheweekspringbootteamproject.model.Dish;
import com.example.menuoftheweekspringbootteamproject.model.Ingredient;
import com.example.menuoftheweekspringbootteamproject.service.DishService;
import com.example.menuoftheweekspringbootteamproject.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public class IngredientController {

    @Autowired
    private IngredientService service;

    @GetMapping("/ingredients")
    public String findAll(Model model){
        List<Ingredient> ingredients = service.findAll();
        model.addAttribute("ingredientList", ingredients);

        return "ingredients";
    }

    @GetMapping("/ingredients/new")
    public String showAddIngredient(Model model){
        model.addAttribute("ingredients", new Ingredient());
        model.addAttribute("pageTitle", "Add New Ingredient");
        return "ingredients_form";
    }

    @PostMapping("/ingredient/save")
    public String saveIngredient(Ingredient ingredient, RedirectAttributes ra){
        service.save(ingredient);
        ra.addFlashAttribute("message", "The ingredient has been saved succesfully");
        return "redirect:/ingredients";
    }


    @GetMapping("/dishes/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){
        Ingredient ingredient = service.get(id);
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("pageTitle", "Edit User (with ID: " + id + ")");

        return "ingredient_form";
    }

    @GetMapping("/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable("id") Integer id, RedirectAttributes ra){
        service.deleteById(id);
        ra.addFlashAttribute("message", "The ingredient ID: " + id + " has been deleted ");
        return "redirect:/ingredients";

    }



}
