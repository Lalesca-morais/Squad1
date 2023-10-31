package br.com.zup.squad1.controller;

import br.com.zup.squad1.controller.request.RecipeDTORequest;
import br.com.zup.squad1.dto.RecipeDTO;
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
    public ResponseEntity<Object> registerRecipe(@Valid @RequestBody RecipeDTORequest recipeDTORequest) {
        RecipeModel recipeModel = new RecipeModel();
        BeanUtils.copyProperties(recipeDTORequest, recipeModel);

        try {
            RecipeModel createdRecipe = recipeService.registerRecipe(recipeModel);

            RecipeDTORequest createdRecipeDTORequest = new RecipeDTORequest();

            BeanUtils.copyProperties(createdRecipe, createdRecipeDTORequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipeDTORequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é permitido valores nulos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultRecipe(@PathVariable Long id) {
        RecipeModel recipe = recipeService.consultRecipe(id);

        if (recipe != null) {
            RecipeDTO recipeDTO = new RecipeDTO();
            BeanUtils.copyProperties(recipe, recipeDTO);
            return ResponseEntity.status(HttpStatus.OK).body(recipeDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receita não encontrada");
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
    public ResponseEntity<Object> changeRecipe(@PathVariable Long id, @Valid @RequestBody RecipeModel newRecipe) {
        try {
            RecipeModel recipeChanged = recipeService.changeRecipe(id, newRecipe);

            if (recipeChanged != null) {
                RecipeDTO recipeDTO = new RecipeDTO();
                BeanUtils.copyProperties(recipeChanged, recipeDTO);
                return ResponseEntity.status(HttpStatus.OK).body(recipeDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receita não encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requisição inválida");
        }
    }
}
