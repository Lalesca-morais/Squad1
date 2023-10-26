package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.IngredientDTO;
import br.com.zup.squad1.model.Ingredient;
import br.com.zup.squad1.service.ExpiredIngredientsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/expired-ingredients")
@Tag(name = "Expired Ingredients")
public class ExpiredIngredientsController {
    private final ExpiredIngredientsService expiredIngredientsService;
    @Autowired
    public ExpiredIngredientsController(ExpiredIngredientsService expiredIngredientsService) {
        this.expiredIngredientsService = expiredIngredientsService;
    }
    @GetMapping
    public List<IngredientDTO> listExpiredIngredients(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate currentDate) {
        List<Ingredient> expiredIngredients = expiredIngredientsService.listExpiredIngredients(currentDate);
        List<IngredientDTO> ingredientDTOs = new ArrayList<>();

        for (Ingredient ingredient : expiredIngredients) {
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
}


