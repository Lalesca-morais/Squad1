package br.com.zup.squad1.service;

import br.com.zup.squad1.model.Ingredient;
import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.repository.IngredientRepository;
import br.com.zup.squad1.repository.RecipeIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    public List<Map<String, Object>> notifyIngredientsNearExpiration() {
        LocalDate today = LocalDate.now();
        LocalDate dateExpiration = today.plusDays(3);

        List<Ingredient> ingredientsCloseToExpiration = ingredientRepository.findByValidityBetween(today, dateExpiration);

        List<Map<String, Object>> result = new ArrayList<>();

        for (Ingredient ingredient : ingredientsCloseToExpiration) {

            Map<String, Object> ingredientInfo = new HashMap<>();

            ingredientInfo.put("id", ingredient.getId());
            ingredientInfo.put("name", ingredient.getName());
            ingredientInfo.put("validity", ingredient.getValidity());

            List<RecipeModel> recipeIngredients = recipeIngredientRepository.findRecipesByIngredientId(ingredient.getId());

            ingredientInfo.put("recipe_ingredient", recipeIngredients);

            result.add(ingredientInfo);
        }

        return result;
    }
}