package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.RecipeDTO;
import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.service.RecipeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @PostMapping
    public ResponseEntity<RecipeModel> registerRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeModel recipeModel = new RecipeModel();
        BeanUtils.copyProperties(recipeDTO, recipeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.registerRecipe(recipeModel));
    }
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> consultRecipe(@PathVariable Long id) {
        RecipeModel recipe = recipeService.consultRecipe(id);
        if (recipe != null) {
            RecipeDTO recipeDTO = new RecipeDTO();
            BeanUtils.copyProperties(recipe, recipeDTO);
            return ResponseEntity.status(HttpStatus.OK).body(recipeDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        try {
            recipeService.deleteRecipe(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> changeRecipe(@PathVariable Long id, @RequestBody RecipeModel newRecipe) {
        try {
            RecipeModel recipeChanged = recipeService.changeRecipe(id, newRecipe);

            if (recipeChanged != null) {
                RecipeDTO recipeDTO = new RecipeDTO();
                BeanUtils.copyProperties(recipeChanged, recipeDTO);
                return ResponseEntity.status(HttpStatus.OK).body(recipeDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
