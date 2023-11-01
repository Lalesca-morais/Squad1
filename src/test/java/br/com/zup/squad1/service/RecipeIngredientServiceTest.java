package br.com.zup.squad1.service;

import br.com.zup.squad1.dto.RecipeDTO;
import br.com.zup.squad1.dto.RecipeIngredientResponseDTO;
import br.com.zup.squad1.exceptions.IngredientNotFound;
import br.com.zup.squad1.model.Ingredient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import br.com.zup.squad1.model.RecipeIngredientModel;
import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.repository.RecipeIngredientRepository;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecipeIngredientServiceTest {
    @Mock
    private RecipeIngredientRepository repository;

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private RecipeIngredientService service;

    @Test
    public void testSave() {
        RecipeIngredientModel recipeIngredient = new RecipeIngredientModel();
        when(repository.save(recipeIngredient)).thenReturn(recipeIngredient);
        RecipeIngredientModel result = service.save(recipeIngredient);
        verify(repository, times(1)).save(recipeIngredient);
        assertEquals(recipeIngredient, result);
    }

    @Test
    public void testFindAll() {
        List<RecipeIngredientModel> recipeIngredients = new ArrayList<>();
        when(repository.findAll()).thenReturn(recipeIngredients);
        List<RecipeIngredientModel> result = service.findAll();
        verify(repository, times(1)).findAll();
        assertEquals(recipeIngredients, result);
    }

    @Test
    public void testDelete() {
        RecipeIngredientModel recipeIngredient = new RecipeIngredientModel();
        service.delete(recipeIngredient);
        verify(repository, times(1)).delete(recipeIngredient);
    }

    @Test
    public void testFindById() {
        Optional<RecipeIngredientModel> recipeIngredientOptional = Optional.of(new RecipeIngredientModel());
        when(repository.findById(1L)).thenReturn(recipeIngredientOptional);
        Optional<RecipeIngredientModel> result = service.findById(1L);
        assertEquals(recipeIngredientOptional, result);
    }

    @Test
    public void testFindRecipesByIngredientId() {
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setId(1L);
        recipeModel.setName("Pão de Mel");
        recipeModel.setPreparation("Misture mel, açúcar mascavo, canela e gengibre. Adicione farinha, leite e " +
                "bicarbonato de sódio. Asse em formas individuais e cubra com chocolate derretido.");
        recipeModel.setDifficulty("Médio");

        List<RecipeModel> recipes = new ArrayList<>();
        recipes.add(recipeModel);
        when(repository.findRecipesByIngredientId(1L)).thenReturn(recipes);
        List<RecipeModel> result = service.findRecipesByIngredientId(1L);
        assertEquals(recipes, result);
    }

    @Test
    public void testFindRecipeIngredientsByRecipeId() {
        List<RecipeIngredientModel> recipeIngredients = new ArrayList<>();
        when(repository.findRecipeIngredientsByRecipeId(2L)).thenReturn(recipeIngredients);
        List<RecipeIngredientModel> result = service.findRecipeIngredientsByRecipeId(2L);
        assertEquals(recipeIngredients, result);
    }

    @Test
    void testFindRecipesByIngredientsNames() {
        RecipeModel firstRecipe = new RecipeModel();
        firstRecipe.setId(1L);
        firstRecipe.setName("Pão de Mel");
        firstRecipe.setPreparation("Misture mel, açúcar, canela e gengibre. Adicione farinha, leite e " +
                "bicarbonato de sódio. Asse em formas individuais e cubra com chocolate derretido.");
        firstRecipe.setDifficulty("Médio");

        RecipeModel secondRecipe = new RecipeModel();
        secondRecipe.setId(2L);
        secondRecipe.setName("Bolo de Cenoura");
        secondRecipe.setPreparation("Bata no liquidificador cenouras, óleo, ovos e açúcar. Em uma tigela, misture " +
                "a farinha de trigo e o fermento. Combine as duas misturas e asse em uma forma untada. Prepare uma " +
                "cobertura de chocolate e despeje sobre o bolo.");
        secondRecipe.setDifficulty("Fácil");

        List<String> ingredientsName = Arrays.asList("farinha", "açúcar");
        List<RecipeModel> recipes = new ArrayList<>();
        recipes.add(firstRecipe);
        recipes.add(secondRecipe);
        when(repository.findRecipesByIngredientNames(anyList(), anyInt())).thenReturn(recipes);
        List<RecipeModel> result = service.findRecipesByIngredientsNames(ingredientsName, 2);
        assertEquals(recipes, result);
        verify(repository).findRecipesByIngredientNames(ingredientsName, 2);
    }

    @Test
    public void testFormatRecipe() throws IngredientNotFound {
        RecipeModel recipe = new RecipeModel();
        recipe.setId(1L);
        recipe.setName("Bolo de Cenoura");
        recipe.setPreparation("Bata no liquidificador cenouras, óleo, ovos e açúcar. Em uma tigela, misture " +
                "a farinha de trigo e o fermento. Combine as duas misturas e asse em uma forma untada. Prepare uma " +
                "cobertura de chocolate e despeje sobre o bolo.");
        recipe.setDifficulty("Fácil");

        List<RecipeModel> recipes = new ArrayList<>();
        recipes.add(recipe);

        List<RecipeIngredientModel> ingredientModels = new ArrayList<>();
        ingredientModels.add(new RecipeIngredientModel(1L,1L, 1L, "500g"));
        ingredientModels.add(new RecipeIngredientModel(2L,1L, 2L, "3 unidades"));
        ingredientModels.add(new RecipeIngredientModel(3L,1L, 3L, "2 xícaras"));

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(1L, "Cenoura"));
        ingredients.add(new Ingredient(2L, "Ovo"));
        ingredients.add(new Ingredient(3L, "Açúcar"));

        List<RecipeDTO> recipeDTO = new ArrayList<>();
        recipeDTO.add(new RecipeDTO(1L, "Bolo de Cenoura", "Bata no liquidificador cenouras, óleo, ovos e açúcar. Em uma tigela, misture " +
                "a farinha de trigo e o fermento. Combine as duas misturas e asse em uma forma untada. Prepare uma " +
                "cobertura de chocolate e despeje sobre o bolo.", "Fácil", Arrays.asList(
                        new RecipeIngredientResponseDTO(1L, "Cenoura", "500g"),
                        new RecipeIngredientResponseDTO(2L, "Ovo", "3 unidades"),
                        new RecipeIngredientResponseDTO(3L, "Açúcar", "2 xícaras")
                )
        ));

        when(service.findRecipeIngredientsByRecipeId(anyLong())).thenReturn(ingredientModels);
        when(ingredientService.listIngredientById(1L)).thenReturn(ingredients.get(0));
        when(ingredientService.listIngredientById(2L)).thenReturn(ingredients.get(1));
        when(ingredientService.listIngredientById(3L)).thenReturn(ingredients.get(2));

        List<RecipeDTO> result = service.formatRecipe(recipes);
        result.get(0).getIngredients().get(0).setIngredientName("Cenoura");
        result.get(0).getIngredients().get(1).setIngredientName("Ovo");
        result.get(0).getIngredients().get(2).setIngredientName("Açúcar");

        assertEquals(recipeDTO.get(0).getName(), result.get(0).getName());
        assertEquals(recipeDTO.get(0).getPreparation(), result.get(0).getPreparation());
        assertEquals(recipeDTO.get(0).getDifficulty(), result.get(0).getDifficulty());
        assertEquals(recipeDTO.get(0).getIngredients(), result.get(0).getIngredients());
    }
}