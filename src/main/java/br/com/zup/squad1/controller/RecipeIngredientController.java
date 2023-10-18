package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.RecipeDTO;
import br.com.zup.squad1.dto.RecipeIngredientDto;
import br.com.zup.squad1.exeption.IngredientNotFound;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipe-ingredients")
public class RecipeIngredientController {
    @Autowired
    RecipeIngredientService service;

    @PostMapping
    public ResponseEntity<RecipeIngredientModel> save(@RequestBody @Valid RecipeIngredientDto recipeIngredientDto){
        RecipeIngredientModel recipeIngredient = new RecipeIngredientModel();
        recipeIngredient.setId_recipe(recipeIngredientDto.getIdRecipe());
        recipeIngredient.setId_ingredient(recipeIngredientDto.getIdIngredient());
        recipeIngredient.setIngredientQuantity(recipeIngredientDto.getIngredientQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(recipeIngredient));
    }

//    @GetMapping
//    public ResponseEntity<List<RecipeIngredientModel>> getAllRecipeIngredients(){
//        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
//    }

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
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid RecipeIngredientDto recipeIngredientDto){
        Optional<RecipeIngredientModel> recipeIngredientOptional = service.findById(id);
        if(recipeIngredientOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingrediente de Receita não encontrado!");
        }

        var recipeIngredient = recipeIngredientOptional.get();
        BeanUtils.copyProperties(recipeIngredientDto, recipeIngredient);
        return ResponseEntity.status(HttpStatus.OK).body(service.save(recipeIngredient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RecipeDTO>> getRecipesByIngredientId(@PathVariable(value = "id") Long id_ingredient) {
        List<RecipeModel> recipes = service.findRecipesByIngredientId(id_ingredient);
        if (recipes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<RecipeDTO> recipeDTOs = recipes.stream()
                .map(recipe -> {
                    RecipeDTO dto = new RecipeDTO();
                    dto.setId(recipe.getId());
                    dto.setName(recipe.getName());
                    dto.setPreparation(recipe.getPreparation());
                    dto.setDifficulty(recipe.getDifficulty());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body( recipeDTOs);
    }
}
