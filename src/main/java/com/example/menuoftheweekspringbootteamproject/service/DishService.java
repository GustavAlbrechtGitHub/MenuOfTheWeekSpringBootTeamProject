package com.example.menuoftheweekspringbootteamproject.service;

import com.example.menuoftheweekspringbootteamproject.dao.DishRepository;
import com.example.menuoftheweekspringbootteamproject.model.Dish;
import com.example.menuoftheweekspringbootteamproject.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DishService {

    @Autowired
    private DishRepository repository;

    public List<Dish> findAll(){
        Iterable<Dish> dishes = repository.findAll();
        return (List<Dish>) dishes;

    }

    public void save(Dish dish){
        repository.save(dish);
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
}
