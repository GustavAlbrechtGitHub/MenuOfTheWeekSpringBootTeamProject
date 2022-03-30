package com.example.menuoftheweekspringbootteamproject.service;


import com.example.menuoftheweekspringbootteamproject.dao.IngredientRepository;
import com.example.menuoftheweekspringbootteamproject.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@SessionScope
public class IngredientService {

    @Autowired
    private IngredientRepository repository;


    public List<Ingredient> findAll(){
          Iterable<Ingredient> ingredients = repository.findAll();
          return (List<Ingredient>) ingredients;

    }

    public List<Ingredient> findAllById(Integer id){
        Optional<Ingredient> ingredient = repository.findById(id);

        List ingredients = ingredient.map(Collections::singletonList)
                .orElseGet(Collections::emptyList);


        return ingredients;

    }
    public Ingredient findByExactName(String keyName){

        List<Ingredient> list = repository.findByExactName(keyName);

        if (list.isEmpty()){
            return null;
        }
        else return list.get(0);

    }

    public Ingredient save(Ingredient ingredient){
        return repository.save(ingredient);
    }

    public Ingredient get(Integer id){
        Optional<Ingredient> ingredient = repository.findById(id);
        if (ingredient.isPresent()){
            return ingredient.get();
        }  else {
            return null;
        }

    }

    public List<Ingredient> findByKeyword(String keyword){
        return repository.findByKeyword(keyword);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);

    }
}
