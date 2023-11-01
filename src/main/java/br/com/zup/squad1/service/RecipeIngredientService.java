package br.com.zup.squad1.service;

import br.com.zup.squad1.dto.RecipeDTO;
import br.com.zup.squad1.dto.RecipeIngredientResponseDTO;
import br.com.zup.squad1.exceptions.IngredientNotFound;
import br.com.zup.squad1.model.Ingredient;
import jakarta.transaction.Transactional;
import br.com.zup.squad1.model.RecipeIngredientModel;
import br.com.zup.squad1.model.RecipeModel;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.zup.squad1.repository.RecipeIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientService {

    @Autowired
    RecipeIngredientRepository repository;

    @Autowired
    IngredientService service;

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

    @Transactional
    public List<RecipeModel> findRecipesByIngredientsNames(List<String> ingredientsName, int ingredientCount) {
        List<String> ingredientsLower = new ArrayList<>();
        for (String ingredient: ingredientsName) {
            ingredientsLower.add(ingredient.toLowerCase());
        }
        return repository.findRecipesByIngredientNames(ingredientsLower, ingredientCount);
    }

    @Transactional
    public List<RecipeIngredientModel> findRecipeIngredientsByRecipeId(Long id_recipe) {
        return repository.findRecipeIngredientsByRecipeId(id_recipe);
    }

    public List<RecipeDTO> formatRecipe(List<RecipeModel> recipes){
        return recipes.stream()
                .map(recipe -> {
                    RecipeDTO dto = new RecipeDTO();
                    dto.setId(recipe.getId());
                    dto.setName(recipe.getName());
                    dto.setPreparation(recipe.getPreparation());
                    dto.setDifficulty(recipe.getDifficulty());

                    List<RecipeIngredientModel> ingredientModels = findRecipeIngredientsByRecipeId(recipe.getId());
                    List<RecipeIngredientResponseDTO> ingredientDtos = new ArrayList<>();
                    for (RecipeIngredientModel recipeIngredient: ingredientModels) {
                        RecipeIngredientResponseDTO recipeIngredientDto = new RecipeIngredientResponseDTO();
                        try {
                            Ingredient ingredient = service.listIngredientById(recipeIngredient.getId_ingredient());
                            recipeIngredientDto.setIngredientId(recipeIngredient.getId_ingredient());
                            recipeIngredientDto.setIngredientName(ingredient.getName());
                            recipeIngredientDto.setIngredientQuantity(recipeIngredient.getIngredientQuantity());
                        } catch (IngredientNotFound e) {
                            throw new RuntimeException(e);
                        }
                        ingredientDtos.add(recipeIngredientDto);
                    }

                    dto.setIngredients(ingredientDtos);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
