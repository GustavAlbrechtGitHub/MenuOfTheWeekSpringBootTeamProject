package com.example.menuoftheweekspringbootteamproject.controller;

import com.example.menuoftheweekspringbootteamproject.dto.DishListDto;
import com.example.menuoftheweekspringbootteamproject.dto.ShoppingIngredientDto;
import com.example.menuoftheweekspringbootteamproject.model.Dish;
import com.example.menuoftheweekspringbootteamproject.model.Ingredient;
import com.example.menuoftheweekspringbootteamproject.service.DishService;
import com.example.menuoftheweekspringbootteamproject.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DishController {

    private List<Dish> orders = new ArrayList<>();

    @Autowired
    private DishService service;

    @Autowired
    private IngredientService ingredientService;


    @GetMapping("/dishes")
    public String findAll(Model model, String keyword, RedirectAttributes ra) {

        if (keyword != null)
            model.addAttribute("dishList", service.findByKeyword(keyword));
        else {
            List<Dish> dishes = service.findAll();

            model.addAttribute("dishList", dishes);
            ra.addFlashAttribute("message", "The dish has been saved succesfully");
        }

        return "start_page";
    }

    @GetMapping("/dishes/new")
    public String showAddDish(Model model) {

        Dish dish = new Dish();

        model.addAttribute("dish", dish);
        model.addAttribute("pageTitle", "Add New Dish");

        return "dish_form";
    }


    @PostMapping("/dishes/save")
    public String saveDish(Model model, Dish dish, RedirectAttributes ra) {

        if (dish.getId() != null) {
            dish = service.resetDishListOfIngredients(dish);
        }

        dish.setName(dish.getName().toLowerCase());

        Dish savedDish = service.save(dish);

        Ingredient emptyIngredient = new Ingredient();

        ra.addFlashAttribute("message", "The dish has been saved succesfully");
        model.addAttribute("dish", savedDish);
        model.addAttribute("ingredient", emptyIngredient);
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
    public String deleteDish(Model model, @PathVariable("id") Integer id, RedirectAttributes ra) {

        Dish dish = service.findById(id);

        List<Ingredient> ingredients = dish.getIngredients();

        ingredients.stream().forEach(ingredient -> ingredient.getDishes().remove(dish));

        dish.setIngredients(null);

        service.deleteById(id);

        List<Dish> dishes = service.findAll();

        model.addAttribute("dishList", dishes);
        ra.addFlashAttribute("message", "The Dish ID: " + id + " has been deleted ");

        return "redirect:/dishes/start_page";
    }

    @GetMapping("/dishes/like/{id}")
    public String addLike(Model model, @PathVariable("id") Integer id, RedirectAttributes ra) {

        service.like(id);

        List<Dish> dishes = service.findAll();

        model.addAttribute("dishList", dishes);
        ra.addFlashAttribute("message", "The Dish ID: " + id + " has been liked! ");

        return "redirect:/dishes/start_page";
    }

    @GetMapping("/dishes/index")
    public String returnIndex() {

        return "index";
    }

    @GetMapping("/dishes/start_page")
    public String findAll2(Model model) {

        List<Dish> dishes = service.findAll();

        model.addAttribute("dishList", dishes);

        return "start_page";
    }

    @GetMapping("/dishes/view ingredients/{id}")
    public String showIngredients(Model model, @PathVariable("id") Integer id) {

        Dish dish = service.findById(id);

        List<Ingredient> ingredients = dish.getIngredients();

        model.addAttribute("ingredientList", ingredients);

        return "ingredient_page";
    }

    @GetMapping("/dishes/menu")
    public String showWeekMenu() {

        return "week_menu";
    }

    @GetMapping("/dishes/menu/random")
    public String showWeekMenuRandom(Model model, RedirectAttributes ra) {

        List<Dish> allDishes = service.findAll();

        if (allDishes.size() < 7) {

            ra.addFlashAttribute("message", "You need at least 7 dishes to create this menu! ");

            return "redirect:/dishes/menu";
        }

        List<Dish> selectedDishes = service.generateList(allDishes, 7);

        DishListDto dishListDto = new DishListDto();
        dishListDto.setDishList(selectedDishes);

        model.addAttribute("pageTitle", "Random Menu");
        model.addAttribute("generatedList", selectedDishes);
        model.addAttribute("dishListDto", dishListDto);


        return "week_menu_generated";
    }

    @GetMapping("/dishes/menu/vegetarian")
    public String showWeekMenuVegetarian(Model model, RedirectAttributes ra) {

        List<Dish> allDishes = service.findAll();

        List<Dish> allVegetarianDishes = service.getDishesByDescription(allDishes, "Vegetarian");

        if (allVegetarianDishes.size() < 7) {

            ra.addFlashAttribute("message", "You need 7 vegetarian dishes to create this menu! ");

            return "redirect:/dishes/menu";
        }

        List<Dish> selectedDishes = service.generateList(allVegetarianDishes, 7);

        DishListDto dishListDto = new DishListDto();
        dishListDto.setDishList(selectedDishes);

        model.addAttribute("pageTitle", "Vegetarian Menu");
        model.addAttribute("generatedList", selectedDishes);
        model.addAttribute("dishListDto", dishListDto);


        return "week_menu_generated";
    }

    @GetMapping("/dishes/menu/non-vegetarian")
    public String showWeekMenuNonVegetarian(Model model, RedirectAttributes ra) {

        List<Dish> allDishes = service.findAll();

        List<Dish> allNonVegetarianDishes = service.getDishesByDescription(allDishes, "Non-Vegetarian");

        if (allNonVegetarianDishes.size() < 7) {

            ra.addFlashAttribute("message", "You need 7 non-vegetarian dishes to create this menu! ");

            return "redirect:/dishes/menu";
        }

        List<Dish> selectedDishes = service.generateList(allNonVegetarianDishes, 7);

        DishListDto dishListDto = new DishListDto();
        dishListDto.setDishList(selectedDishes);

        model.addAttribute("pageTitle", "Non-Vegetarian Menu");
        model.addAttribute("generatedList", selectedDishes);
        model.addAttribute("dishListDto", dishListDto);


        return "week_menu_generated";
    }

    @GetMapping("/dishes/menu/popular")
    public String showWeekMenuNonPopular(Model model, RedirectAttributes ra) {

        List<Dish> allDishes = service.findAll();

        List<Dish> selectedDishes = new ArrayList<>();

        if (allDishes.size() < 7) {


            ra.addFlashAttribute("message", "You need at least 7 dishes to create this menu! ");

            return "redirect:/dishes/menu";
        }

        allDishes.stream()
                .sorted((d1,d2)->Integer.compare(d2.getLikes(),d1.getLikes()))
                .limit(7)
                .forEach(d -> selectedDishes.add(d) );


        DishListDto dishListDto = new DishListDto();
        dishListDto.setDishList(selectedDishes);

        model.addAttribute("pageTitle", "Popular Menu");
        model.addAttribute("generatedList", selectedDishes);
        model.addAttribute("dishListDto", dishListDto);

        return "week_menu_generated";
    }

    @GetMapping("/dishes/add to orders/{id}")
    public String addDishToOrders(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){

        Dish dish = service.findById(id);
        orders.add(dish);
        ra.addFlashAttribute("message", "The Dish ID: " + id + " has been added to your orders!");

        return "redirect:/dishes/start_page";
    }

    @GetMapping("/dishes/orders")
    public String showOrders(Model model){

        model.addAttribute("orders", orders);
        return "order_page";
    }


    @PostMapping("/dishes/shoppingList")
    public String showShoppingList( DishListDto dishListDto, Model model){

        List<ShoppingIngredientDto> shoppingIngredients = service.generateIngredientListWithQuantity(dishListDto);

        model.addAttribute("ingredients", shoppingIngredients);

        return "shopping_list_page";
    }
}
