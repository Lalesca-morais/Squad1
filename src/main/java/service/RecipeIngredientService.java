package service;

import jakarta.transaction.Transactional;
import model.RecipeIngredientModel;
import org.springframework.beans.factory.annotation.Autowired;
import repository.RecipeIngredientRepository;

import java.util.List;
import java.util.Optional;

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
    public List<Recipe> findRecipesByIngredientId(Long ingredientId) {
        return repository.findRecipesByIngredientId(ingredientId);
    }
}
