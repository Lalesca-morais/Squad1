package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.RecipeDTORequest;
import br.com.zup.squad1.dto.RecipeDTO;
import br.com.zup.squad1.exceptions.RecipeNotFoundException;
import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.service.RecipeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("recipes")
@Tag(name = "Recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @PostMapping
    public ResponseEntity<Object> registerRecipe(@Valid @RequestBody RecipeDTORequest recipeDTO) {
        RecipeModel recipeModel = new RecipeModel();
        BeanUtils.copyProperties(recipeDTO, recipeModel);
        try {
            RecipeModel createdRecipe = recipeService.registerRecipe(recipeModel);
            RecipeDTORequest createdRecipeDTO = new RecipeDTORequest();
            BeanUtils.copyProperties(createdRecipe, createdRecipeDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipeDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um problema ao registrar a receita");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultRecipe(@PathVariable Long id) {
        try {
            RecipeModel recipe = recipeService.consultRecipe(id);
            RecipeDTO recipeDTO = new RecipeDTO();
            BeanUtils.copyProperties(recipe, recipeDTO);
            return ResponseEntity.status(HttpStatus.OK).body(recipeDTO);
        } catch (RecipeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable Long id) {
        try {
            recipeService.deleteRecipe(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receita não encontrada");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> changeRecipe(@PathVariable Long id, @Valid @RequestBody RecipeDTORequest recipeDTORequest) {
        try {
            RecipeModel newRecipe = new RecipeModel(recipeDTORequest.getId(), recipeDTORequest.getName(), recipeDTORequest.getPreparation(), recipeDTORequest.getDifficulty());
            RecipeModel recipeChanged = recipeService.changeRecipe(id, newRecipe);
            RecipeDTO recipeDTO = new RecipeDTO();
            BeanUtils.copyProperties(recipeChanged, recipeDTO);
            return ResponseEntity.status(HttpStatus.OK).body(recipeDTO);
        } catch (RecipeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receita não encontrada");
        }
    }
}
