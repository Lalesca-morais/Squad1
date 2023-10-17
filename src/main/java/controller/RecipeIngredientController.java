package controller;

import dto.RecipeIngredientDto;
import model.RecipeIngredientModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.RecipeIngredientService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recipe-ingredients")
public class RecipeIngredientController {
    @Autowired
    RecipeIngredientService service;

    @PostMapping
    public ResponseEntity<RecipeIngredientModel> save(@RequestBody @Valid RecipeIngredientDto recipeIngredientDto){
        var recipeIngredient = new RecipeIngredientModel();
        BeanUtils.copyProperties(recipeIngredientDto, recipeIngredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(recipeIngredient));
    }

    @GetMapping
    public ResponseEntity<List<RecipeIngredientModel>> getAllRecipeIngredients(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){
        Optional<RecipeIngredientModel> recipeIngredientOptional = service.findById(id);
        if(recipeIngredientOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingrediente de Receita não encontrado!");
        }
        service.delete(recipeIngredientOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Ingrediente de Receita excluído com sucesso!");
    }

    @PutMapping
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid RecipeIngredientDto recipeIngredientDto){
        Optional<RecipeIngredientModel> recipeIngredientOptional = service.findById(id);
        if(recipeIngredientOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingrediente de Receita não encontrado!");
        }

        var recipeIngredient = recipeIngredientOptional.get();
        BeanUtils.copyProperties(recipeIngredientDto, recipeIngredient);
        return ResponseEntity.status(HttpStatus.OK).body(service.save(recipeIngredient));
    }

    public ResponseEntity<Object> getRecipesByIngredientId(@PathVariable(value = "id") Long ingredientId) {
        List<Recipe> recipes = service.findRecipesByIngredientId(ingredientId);
        if (recipes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há receitas cadastradas com esse ingrediente.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(recipes);
    }
}
