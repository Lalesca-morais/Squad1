package br.com.zup.squad1.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import br.com.zup.squad1.model.RecipeIngredientModel;
import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.repository.RecipeIngredientRepository;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecipeIngredientServiceTest {
    @Mock
    private RecipeIngredientRepository repository;

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
}