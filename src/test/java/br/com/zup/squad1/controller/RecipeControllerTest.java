package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.RecipeDTO;
import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeControllerTest {
    @InjectMocks
    private RecipeController recipeController;
    @Mock
    private RecipeService recipeService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testRegisterRecipe() {
//        RecipeDTO recipeDTO = new RecipeDTO();
//        recipeDTO.setName("Test Recipe");
//        recipeDTO.setPreparation("Test Preparation");
//        recipeDTO.setDifficulty("Medium");
//
//        RecipeModel recipeModel = new RecipeModel();
//        BeanUtils.copyProperties(recipeDTO, recipeModel);
//
//        Mockito.when(recipeService.registerRecipe(recipeModel)).thenReturn(recipeModel);
//        ResponseEntity<RecipeModel> response = recipeController.registerRecipe(recipeDTO);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(recipeModel, response.getBody());
//    }
//
//    @Test
//    public void testConsultRecipe() {
//        Long recipeId = 1L;
//        RecipeModel recipeModel = new RecipeModel();
//        recipeModel.setId(recipeId);
//        recipeModel.setName("Test Recipe");
//        recipeModel.setPreparation("Test Preparation");
//        recipeModel.setDifficulty("Easy");
//
//        Mockito.when(recipeService.consultRecipe(recipeId)).thenReturn(recipeModel);
//        ResponseEntity<RecipeDTO> response = recipeController.consultRecipe(recipeId);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        RecipeDTO recipeDTO = response.getBody();
//        assertEquals("Test Recipe", recipeDTO.getName());
//        assertEquals("Test Preparation", recipeDTO.getPreparation());
//        assertEquals("Easy", recipeDTO.getDifficulty());
//    }
//
//    @Test
//    public void testConsultRecipeNotFound() {
//        Long recipeId = 1L;
//
//        Mockito.when(recipeService.consultRecipe(recipeId)).thenReturn(null);
//
//        ResponseEntity<RecipeDTO> response = recipeController.consultRecipe(recipeId);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    public void testDeleteRecipe() {
//        Long recipeId = 1L;
//        Mockito.doNothing().when(recipeService).deleteRecipe(recipeId);
//        ResponseEntity<Void> response = recipeController.deleteRecipe(recipeId);
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//    }
//
//    @Test
//    public void testChangeRecipe() {
//        Long recipeId = 1L;
//        RecipeModel newRecipeModel = new RecipeModel();
//        newRecipeModel.setName("Changed Recipe");
//        newRecipeModel.setPreparation("Changed Preparation");
//        newRecipeModel.setDifficulty("Medium");
//
//        RecipeModel recipeChanged = new RecipeModel();
//        recipeChanged.setId(recipeId);
//        recipeChanged.setName("Test Recipe");
//        recipeChanged.setPreparation("Test Preparation");
//        recipeChanged.setDifficulty("Easy");
//
//        Mockito.when(recipeService.changeRecipe(recipeId, newRecipeModel)).thenReturn(recipeChanged);
//        ResponseEntity<RecipeDTO> response = recipeController.changeRecipe(recipeId, newRecipeModel);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        RecipeDTO recipeDTO = response.getBody();
//        assertEquals("Test Recipe", recipeDTO.getName());
//        assertEquals("Test Preparation", recipeDTO.getPreparation());
//        assertEquals("Easy", recipeDTO.getDifficulty());
//    }
//
//    @Test
//    public void testChangeRecipeNotFound() {
//        Long recipeId = 1L;
//        RecipeModel newRecipeModel = new RecipeModel();
//
//        Mockito.when(recipeService.changeRecipe(recipeId, newRecipeModel)).thenReturn(null);
//        ResponseEntity<RecipeDTO> response = recipeController.changeRecipe(recipeId, newRecipeModel);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
}

