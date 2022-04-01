package com.example.menuoftheweekspringbootteamproject.service;

import com.example.menuoftheweekspringbootteamproject.DishListDto;
import com.example.menuoftheweekspringbootteamproject.dao.DishRepository;
import com.example.menuoftheweekspringbootteamproject.model.Dish;
import com.example.menuoftheweekspringbootteamproject.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


@Service
@SessionScope
public class DishService {

    @Autowired
    private DishRepository repository;


    public List<Dish> findAll(){
        Iterable<Dish> dishes = repository.findAll();
        return (List<Dish>) dishes;

    }

    public List<Dish> findAllById(List<Integer> ids){
        Iterable<Dish> selectedDishes = repository.findAllById(ids);

        return (List<Dish>) selectedDishes;
    }

    public Dish save(Dish dish){

        return repository.save(dish);
    }

    public Dish findById(Integer id){
        return repository.findById(id).get();
    }


    public Dish get(Integer id){
        Optional<Dish> dish = repository.findById(id);
        if (dish.isPresent()){
            return dish.get();
        }  else {
            return null;
        }

    }

    public List<Dish> findByKeyword(String keyword){
        return repository.findByKeyword(keyword);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public void like(Integer id) {

        Dish dish = repository.findById(id).get();

        dish.setLikes(1);
        repository.save(dish);
    }

    public List<Dish> generateList(List<Dish> allDishes, Integer listSize){

        List<Integer> allDishesId = new ArrayList<>();
        List<Integer> selectedIds = new ArrayList<>();

        allDishes.stream().forEach(dish -> allDishesId.add(dish.getId()));

        ThreadLocalRandom.current()
                .ints(0, allDishesId.size())
                .distinct().limit(listSize)
                .forEach(d -> selectedIds.add(allDishesId.get(d)));

        return findAllById(selectedIds);
    }

    public Dish resetDishListOfIngredients(Dish dish){

        Dish existingDish = findById(dish.getId());

        List<Ingredient> ingredients = existingDish.getIngredients();
        ingredients.stream().forEach(ingredient -> ingredient.getDishes().remove(existingDish));

        existingDish.setIngredients(new ArrayList<>());
        existingDish.setDescription(dish.getDescription());

        return existingDish;
    }

    public List<Ingredient> getIngredientsFromDishDto (DishListDto dishListDto){

        List<Ingredient> ingredients = new ArrayList<>();
        List<Integer> menuDishesId = new ArrayList<>();

        dishListDto.getDishList().stream().forEach(dish -> menuDishesId.add(dish.getId()));

        List<Dish> menuDishes = findAllById(menuDishesId);

        menuDishes.stream().forEach(dish -> ingredients.addAll(dish.getIngredients()));

        return ingredients;
    }



}
