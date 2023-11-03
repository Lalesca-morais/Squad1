package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.RecipeIngredientRequestDTO;
import br.com.zup.squad1.model.RecipeIngredientModel;
import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.service.RecipeIngredientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecipeIngredientControllerTest {
    @Mock
    private RecipeIngredientService service;

    @InjectMocks
    private RecipeIngredientController controller;

    @Test
    @DisplayName("Deve retornar sucesso ao salvar ingrediente de receita")
    public void testSave(){
        RecipeIngredientRequestDTO recipeIngredientDto = new RecipeIngredientRequestDTO(1L, 1L, "120g");
        RecipeIngredientModel recipeIngredientModel = new RecipeIngredientModel();

        recipeIngredientModel.setId_recipe(recipeIngredientDto.getIdRecipe());
        recipeIngredientModel.setId_ingredient(recipeIngredientDto.getIdIngredient());
        recipeIngredientModel.setIngredientQuantity(recipeIngredientDto.getIngredientQuantity());

        when(service.save(any(RecipeIngredientModel.class))).thenReturn(recipeIngredientModel);

        ResponseEntity<RecipeIngredientModel> response = controller.save(recipeIngredientDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recipeIngredientModel, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar que o ingrediente de receita foi excluído com sucesso")
    public void testDelete() {
        when(service.findById(1L)).thenReturn(Optional.of(new RecipeIngredientModel()));

        ResponseEntity<Object> response = controller.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ingrediente de Receita excluído com sucesso!", response.getBody());
    }

    @Test
    @DisplayName("Deve retornar que o ingrediente de receita não foi encontrado")
    public void testDeleteNotFound() {
        when(service.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = controller.delete(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Ingrediente de Receita não encontrado!", response.getBody());
    }

    @Test
    @DisplayName("Deve retornar sucesso ao atualizar ingrediente de receita")
    public void testUpdate() {
        RecipeIngredientModel recipeIngredientModel = new RecipeIngredientModel();
        recipeIngredientModel.setId_recipe(1L);
        recipeIngredientModel.setId_ingredient(2L);
        recipeIngredientModel.setIngredientQuantity("30g");

        when(service.findById(1L)).thenReturn(Optional.of(recipeIngredientModel));
        when(service.save(any(RecipeIngredientModel.class))).thenReturn(recipeIngredientModel);

        RecipeIngredientRequestDTO recipeIngredientDto = new RecipeIngredientRequestDTO(1L, 2L, "30g");
        ResponseEntity<Object> response = controller.update(1L, recipeIngredientDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Deve retornar que o ingrediente de receita não foi encontrado")
    public void testUpdateNotFound() {
        when(service.findById(3L)).thenReturn(Optional.empty());

        RecipeIngredientRequestDTO recipeIngredientDto = new RecipeIngredientRequestDTO(5L, 1L, "250ml");
        ResponseEntity<Object> response = controller.update(3L, recipeIngredientDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Ingrediente de Receita não encontrado!", response.getBody());
    }

    @Test
    @DisplayName("Deve retornar uma receita com o id do ingrediente de receita informado")
    public void testGetRecipesByIngredientId() {
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setId(1L);
        recipeModel.setName("Pão de Mel");
        recipeModel.setPreparation("Misture mel, açúcar mascavo, canela e gengibre. Adicione farinha, leite e " +
                "bicarbonato de sódio. Asse em formas individuais e cubra com chocolate derretido.");
        recipeModel.setDifficulty("Médio");

        RecipeIngredientModel ingredientModel = new RecipeIngredientModel();
        ingredientModel.setId_ingredient(2L);
        ingredientModel.setIngredientQuantity("3");

        List<RecipeModel> recipes = new ArrayList<>();
        recipes.add(recipeModel);

        when(service.findRecipesByIngredientId(1L)).thenReturn(recipes);
        ResponseEntity<Object> response = controller.getRecipesByIngredientId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service).findRecipesByIngredientId(1L);
    }

    @Test
    @DisplayName("Deve retornar que não há receitas com o id do ingrediente informado")
    public void testGetRecipesByIngredientIdNotFound() {
        when(service.findRecipesByIngredientId(1L)).thenReturn(new ArrayList<>());

        ResponseEntity<Object> response = controller.getRecipesByIngredientId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Não há receitas cadastradas com o ingrediente informado!", response.getBody());
    }

    @Test
    @DisplayName("Deve retornar uma lista de receitas com os dois ingredientes informados pelo nome")
    public void testGetRecipesByIngredientsName() {
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

        when(service.findRecipesByIngredientsNames(ingredientsName, 2)).thenReturn(recipes);
        ResponseEntity<Object> response = controller.getRecipesByIngredientsName(ingredientsName, 2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service).findRecipesByIngredientsNames(ingredientsName, 2);
    }

    @Test
    @DisplayName("Deve retornar que não há receitas com o nome do ingrediente informado")
    public void testGetRecipesByIngredientsNameNotFound() {
        List<String> ingredientsName = Arrays.asList("farinha", "açúcar");

        when(service.findRecipesByIngredientsNames(ingredientsName, 2)).thenReturn(new ArrayList<>());

        ResponseEntity<Object> response = controller.getRecipesByIngredientsName(ingredientsName, 2);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Não há receitas cadastradas com os ingredientes informados!", response.getBody());
    }
}