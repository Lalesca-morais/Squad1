package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.IngredientDTO;
import br.com.zup.squad1.exceptions.IngredientNotFound;
import br.com.zup.squad1.model.Ingredient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.zup.squad1.service.IngredientService;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public ResponseEntity<Ingredient> addIngredient(@Valid @RequestBody IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getName());
        ingredient.setValidity(ingredientDTO.getValidity());
        ingredient.setAmount(ingredientDTO.getAmount());
        ingredient.setState(ingredientDTO.getState());
        ingredient.setProductType(ingredientDTO.getProductType());

        if (ingredientDTO.getValidity() == null) {
            LocalDate dueDate = ingredientService.calculateDueDate(ingredient);
            ingredient.setValidity(dueDate);
        } else {
            ingredient.setValidity(ingredientDTO.getValidity());
        }

        Ingredient saveIngredient = ingredientService.addIngredient(ingredient);
        return new ResponseEntity<>(saveIngredient, HttpStatus.CREATED);
    }

    @GetMapping
    public List<IngredientDTO> listAllIngredients() {
        List<Ingredient> ingredients = ingredientService.listAllIngredients();
        List<IngredientDTO> ingredientDTOs = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setId(ingredient.getId());
            ingredientDTO.setName(ingredient.getName());
            ingredientDTO.setValidity(ingredient.getValidity());
            ingredientDTO.setAmount(ingredient.getAmount());
            ingredientDTO.setProductType(ingredient.getProductType());
            ingredientDTO.setState(ingredient.getState());
            ingredientDTOs.add(ingredientDTO);
        }
        return ingredientDTOs;
    }

    @GetMapping("/{id}")
    public IngredientDTO listIngredientById(@PathVariable Long id) throws IngredientNotFound {
        Ingredient ingredient = ingredientService.listIngredientById(id);

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setValidity(ingredient.getValidity());
        ingredientDTO.setAmount(ingredient.getAmount());
        ingredientDTO.setProductType(ingredient.getProductType());
        ingredientDTO.setState(ingredient.getState());

        return ingredientDTO;
    }

    @PutMapping("/{id}")
    public IngredientDTO updateIngredient(@PathVariable Long id, @Valid @RequestBody IngredientDTO ingredientDTO) throws IngredientNotFound {

        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName(ingredientDTO.getName());
        ingredient.setValidity(ingredientDTO.getValidity());
        ingredient.setAmount(ingredientDTO.getAmount());
        ingredient.setState(ingredientDTO.getState());
        ingredient.setProductType(ingredientDTO.getProductType());

        Ingredient updatedIngredient = ingredientService.updateIngredient(id, ingredient);

        IngredientDTO updatedIngredientDTO = new IngredientDTO();
        updatedIngredientDTO.setId(updatedIngredient.getId());
        updatedIngredientDTO.setName(updatedIngredient.getName());
        updatedIngredientDTO.setValidity(updatedIngredient.getValidity());
        updatedIngredientDTO.setAmount(updatedIngredient.getAmount());
        updatedIngredientDTO.setProductType(ingredient.getProductType());
        updatedIngredientDTO.setState(ingredient.getState());

        return updatedIngredientDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
