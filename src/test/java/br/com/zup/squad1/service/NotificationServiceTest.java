package br.com.zup.squad1.service;

import br.com.zup.squad1.model.Ingredient;
import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.repository.IngredientRepository;
import br.com.zup.squad1.repository.RecipeIngredientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private RecipeIngredientRepository recipeIngredientRepository;

    @Test
    void testNotifyIngredientsNearExpiration(){
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Farinha");
        ingredient.setValidity(LocalDate.of(2023, 10, 27));

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        RecipeModel recipe = new RecipeModel();
        recipe.setId(1L);
        recipe.setName("Bolo");
        recipe.setPreparation("Descrição da preparação....");
        recipe.setDifficulty("Fácil");

        List<RecipeModel> recipes = new ArrayList<>();
        recipes.add(recipe);

        LocalDate today = LocalDate.now();
        LocalDate dateExpiration = today.plusDays(3);

        when(ingredientRepository.findByValidityBetween(today, dateExpiration)).thenReturn(ingredients);
        when(recipeIngredientRepository.findRecipesByIngredientId(1L)).thenReturn(recipes);

        List<Map<String, Object>> result = notificationService.notifyIngredientsNearExpiration();

        assertEquals(1, result.size());
    }
}