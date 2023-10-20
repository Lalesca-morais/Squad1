package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.RecipeDTO;
import br.com.zup.squad1.dto.RecipeIngredientDto;
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
import java.util.List;
import java.util.Optional;

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
        RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto(1L, 1L, "120g");
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

        RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto(1L, 2L, "30g");
        ResponseEntity<Object> response = controller.update(1L, recipeIngredientDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Deve retornar que o ingrediente de receita não foi encontrado")
    public void testUpdateNotFound() {
        when(service.findById(3L)).thenReturn(Optional.empty());

        RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto(5L, 1L, "250ml");
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

        List<RecipeIngredientModel> ingredientModels = new ArrayList<>();
        ingredientModels.add(ingredientModel);

        when(service.findRecipesByIngredientId(1L)).thenReturn(recipes);
        when(service.findRecipeIngredientsByRecipeId(recipeModel.getId())).thenReturn(ingredientModels);

        ResponseEntity<List<RecipeDTO>> response = controller.getRecipesByIngredientId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());

        RecipeDTO dto = response.getBody().get(0);
        assertEquals(recipeModel.getId(), dto.getId());
        assertEquals(recipeModel.getName(), dto.getName());
        assertEquals(recipeModel.getPreparation(), dto.getPreparation());
        assertEquals(recipeModel.getDifficulty(), dto.getDifficulty());

        List<RecipeIngredientDto> ingredientDtos = dto.getIngredients();
        assertEquals(1, ingredientDtos.size());

        RecipeIngredientDto ingredientDto = ingredientDtos.get(0);
        assertEquals(recipeModel.getId(), ingredientDto.getIdRecipe());
        assertEquals(ingredientModel.getId_ingredient(), ingredientDto.getIdIngredient());
        assertEquals(ingredientModel.getIngredientQuantity(), ingredientDto.getIngredientQuantity());
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia com o id do ingrediente de receita informado")
    public void testGetRecipesByIngredientIdNotFound() {
        when(service.findRecipesByIngredientId(1L)).thenReturn(new ArrayList<>());

        ResponseEntity<List<RecipeDTO>> response = controller.getRecipesByIngredientId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}