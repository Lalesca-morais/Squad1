package br.com.zup.ingredients.controller;

import br.com.zup.ingredients.exceptions.IngredientNotFound;
import br.com.zup.ingredients.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.zup.ingredients.service.IngredientService;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        Ingredient saveIngredient = ingredientService.addIngredient(ingredient);
        return new ResponseEntity<>(saveIngredient, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Ingredient> listAllIngredients() {
        return ingredientService.listAllIngredients();
    }

    @GetMapping("/{id}")
    public Ingredient listIngredientById(@PathVariable Long id) throws IngredientNotFound {
        return ingredientService.listIngredientById(id);
    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) throws IngredientNotFound {
        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
    }
}
