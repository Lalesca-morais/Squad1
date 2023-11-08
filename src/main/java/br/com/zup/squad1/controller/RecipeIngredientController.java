package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.RecipeIngredientRequestDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import br.com.zup.squad1.model.RecipeIngredientModel;
import br.com.zup.squad1.model.RecipeModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.zup.squad1.service.RecipeIngredientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipe-ingredients")
@Tag(name = "Recipes and ingredients")
public class RecipeIngredientController {
    @Autowired
    RecipeIngredientService service;

    @PostMapping
    public ResponseEntity<RecipeIngredientModel> save(@RequestBody @Valid RecipeIngredientRequestDTO recipeIngredientDto){
        RecipeIngredientModel recipeIngredient = new RecipeIngredientModel();
        recipeIngredient.setId_recipe(recipeIngredientDto.getIdRecipe());
        recipeIngredient.setId_ingredient(recipeIngredientDto.getIdIngredient());
        recipeIngredient.setIngredientQuantity(recipeIngredientDto.getIngredientQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(recipeIngredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){
        Optional<RecipeIngredientModel> recipeIngredientOptional = service.findById(id);
        if(recipeIngredientOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingrediente de Receita não encontrado!");
        }
        service.delete(recipeIngredientOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Ingrediente de Receita excluído com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid RecipeIngredientRequestDTO recipeIngredientDto){
        Optional<RecipeIngredientModel> recipeIngredientOptional = service.findById(id);
        if(recipeIngredientOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingrediente de Receita não encontrado!");
        }

        var recipeIngredient = recipeIngredientOptional.get();
        BeanUtils.copyProperties(recipeIngredientDto, recipeIngredient);
        return ResponseEntity.status(HttpStatus.OK).body(service.save(recipeIngredient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRecipesByIngredientId(@PathVariable(value = "id") Long id_ingredient) {
        List<RecipeModel> recipes = service.findRecipesByIngredientId(id_ingredient);
        if (recipes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há receitas cadastradas com o ingrediente informado!");
        } else return ResponseEntity.status(HttpStatus.OK).body(service.formatRecipe(recipes));
    }

    @GetMapping("/IngredientsName")
    public ResponseEntity<Object> getRecipesByIngredientsName(@RequestParam List<String> ingredientsName, @RequestParam int ingredientCount) {
        List<RecipeModel> recipes = service.findRecipesByIngredientsNames(ingredientsName, ingredientCount);
        if (recipes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há receitas cadastradas com os ingredientes informados!");
        } else return ResponseEntity.status(HttpStatus.OK).body(service.formatRecipe(recipes));
    }
}
