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

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DishController {

    @Autowired
    private DishService service;

    @Autowired
    private IngredientService ingredientService;


    @GetMapping("/dishes")
    public String findAll(Model model, String keyword) {
        if (keyword != null)
            model.addAttribute("dishList", service.findByKeyword(keyword));
        else {
            List<Dish> dishes = service.findAll();
            model.addAttribute("dishList", dishes);
        }
        return "startPage";
    }
      /* if (keyword !=null)
            model.addAttribute("userList", service.findByKeyword(keyword));
        else {

        List<User> users = service.findAll();
        model.addAttribute("userList", users);
    }
        return "users";*/

    @GetMapping("/dishes/new")
    public String showAddDish(Model model) {
        Dish dish = new Dish();
        model.addAttribute("dish", dish);
        model.addAttribute("pageTitle", "Add New Dish");
        return "dish_form";
    }


    @PostMapping("/dishes/save")
    public String saveDish(Model model, Dish dish, RedirectAttributes ra) {

        // If editing existing Dish, resets ingredient list
        if (dish.getId() != null) {

            dish = service.findById(dish.getId());

            List<Ingredient> ingredients = dish.getIngredients();
            for (Ingredient i : ingredients) {
                i.getDishes().remove(dish);
            }
            dish.setIngredients(new ArrayList<>());
        }

        dish.setName(dish.getName().toLowerCase());

        Dish savedDish = service.save(dish);

        ra.addFlashAttribute("message", "The dish has been saved succesfully");
        Ingredient ingredient = new Ingredient();

        model.addAttribute("dish", savedDish);
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("pageTitle", "Add New Ingredient");

        return "ingredient_form";
    }


    @GetMapping("/dishes/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {

        Dish savedDish = service.findById(id);

        model.addAttribute("dish", savedDish);
        model.addAttribute("pageTitle", "Edit Dish");

        return "edit_form";
    }

    @GetMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable("id") Integer id, RedirectAttributes ra) {
        Dish dish = service.findById(id);
        List<Ingredient> ingredients = dish.getIngredients();
        for (Ingredient i : ingredients) {
            i.getDishes().remove(dish);
        }
        dish.setIngredients(null);
        service.deleteById(id);
        ra.addFlashAttribute("message", "The Dish ID: " + id + " has been deleted ");
        return "startPage";

    }

    @GetMapping("/dishes/like/{id}")
    public String addLike(Model model, @PathVariable("id") Integer id, RedirectAttributes ra) {
        service.like(id);
        List<Dish> dishes = service.findAll();
        model.addAttribute("dishList", dishes);
        ra.addFlashAttribute("message", "The Dish ID: " + id + " has been liked! ");
        return "startPage";
    }

    @GetMapping("/dishes/index")
    public String returnIndex() {
        return "index";


    }

    @GetMapping("/dishes/startpage")
    public String findAll2(Model model) {

        List<Dish> dishes = service.findAll();
        model.addAttribute("dishList", dishes);

        return "startPage";
    }


}
