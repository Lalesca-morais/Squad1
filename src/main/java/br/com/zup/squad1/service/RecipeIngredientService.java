package br.com.zup.squad1.service;

import jakarta.transaction.Transactional;
import br.com.zup.squad1.model.RecipeIngredientModel;
import br.com.zup.squad1.model.RecipeModel;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.zup.squad1.repository.RecipeIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeIngredientService {

    @Autowired
    RecipeIngredientRepository repository;

    @Transactional
    public RecipeIngredientModel save(RecipeIngredientModel recipeIngredient) {
        return repository.save(recipeIngredient);
    }

    public List<RecipeIngredientModel> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void delete(RecipeIngredientModel recipeIngredient){
        repository.delete(recipeIngredient);
    }

    public Optional<RecipeIngredientModel> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public List<RecipeModel> findRecipesByIngredientId(Long ingredientId) {
        return repository.findRecipesByIngredientId(ingredientId);
    }
}
