package com.example.menuoftheweekspringbootteamproject.controller;

import com.example.menuoftheweekspringbootteamproject.dao.IngredientRepository;
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

import java.util.List;

@Controller
public class DishController {

    @Autowired
    private DishService service;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/dishes")
    public String findAll(Model model){
        List<Dish> dishes = service.findAll();
        model.addAttribute("dishList", dishes);

        return "index";
    }

    @GetMapping("/dishes/new")
    public String showAddDish(Model model){
        Dish dish = new Dish();
        model.addAttribute("dish", dish);
        model.addAttribute("pageTitle", "Add New Dish");
        return "dish_form";
    }


    @PostMapping("/dishes/save/{id}")
    public String saveDish(Model model, Dish dish, RedirectAttributes ra, @PathVariable("id") Integer id ){
        service.save(dish);

        ra.addFlashAttribute("message", "The dish has been saved succesfully");
        Ingredient ingredient = new Ingredient();

        model.addAttribute("ingredient", ingredient);
        return "ingredient_form";
    }


    @GetMapping("/dishes/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){
        Dish dish = service.get(id);
        model.addAttribute("dish", dish);
        model.addAttribute("pageTitle", "Edit User (with ID: " + id + ")");

        return "dish_form";
    }

    @GetMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable("id") Integer id, RedirectAttributes ra){
        service.deleteById(id);
        ra.addFlashAttribute("message", "The Dish ID: " + id + " has been deleted ");
        return "redirect:/index";

    }

    @PostMapping("/dishes/like/{id}")
    public String addLike(Model model, @PathVariable("id") Integer id, RedirectAttributes ra){
        service.like(id);
        ra.addFlashAttribute("message", "The Dish ID: " + id + " has been liked! ");
        return "redirect:/index";
    }


}
