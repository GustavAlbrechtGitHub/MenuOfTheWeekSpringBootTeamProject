package com.example.menuoftheweekspringbootteamproject.controller;


import com.example.menuoftheweekspringbootteamproject.model.Dish;
import com.example.menuoftheweekspringbootteamproject.model.Ingredient;
import com.example.menuoftheweekspringbootteamproject.service.DishService;
import com.example.menuoftheweekspringbootteamproject.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IngredientController {

    @Autowired
    private IngredientService service;

    @Autowired
    private DishService dishService;

    @GetMapping("/ingredients")
    public String findAll(Model model) {

        List<Ingredient> ingredients = service.findAll();
        model.addAttribute("ingredientList", ingredients);

        return "ingredients";
    }

    @GetMapping("/ingredients/findById/{id}")
    public String findAllById(Model model, @PathVariable("id") int id) {

        List<Ingredient> ingredients = service.findAllById(id);
        model.addAttribute("ingredientList", ingredients);

        return "ingredients";
    }


    @PostMapping("/ingredients/save/{id}")
    public String saveIngredient(Ingredient ingredient, RedirectAttributes ra, Model model, @PathVariable("id") Integer id) {

        Dish savedDish = dishService.findById(id);

        ingredient.setIngredientName(ingredient.getIngredientName().toLowerCase());

        if (service.findByExactName(ingredient.getIngredientName()) != null) {
            ingredient = service.findByExactName(ingredient.getIngredientName());
        }

        service.updateIngredientAndDishesLists(savedDish, ingredient);

        service.save(ingredient);

        Ingredient emptyIngredient = new Ingredient();

        ra.addFlashAttribute("message", "The ingredient has been saved succesfully");
        model.addAttribute("dish", savedDish);
        model.addAttribute("ingredient", emptyIngredient);
        model.addAttribute("pageTitle", "Add New Ingredient");

        return "ingredient_form";
    }


    @GetMapping("/ingredient/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {

        Ingredient ingredient = service.get(id);

        model.addAttribute("ingredient", ingredient);
        model.addAttribute("pageTitle", "Edit User (with ID: " + id + ")");

        return "redirect:/ingredient_form";
    }

    @GetMapping("/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable("id") Integer id, RedirectAttributes ra) {

        service.deleteById(id);

        ra.addFlashAttribute("message", "The ingredient ID: " + id + " has been deleted ");

        return "redirect:/ingredients";
    }
}
