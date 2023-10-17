package br.com.zup.ingredients.service;

import br.com.zup.ingredients.exceptions.IngredientNotFound;
import br.com.zup.ingredients.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.ingredients.repository.IngredientRepository;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> listAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient listIngredientById(Long id) throws IngredientNotFound {
        return ingredientRepository.findById(id).orElseThrow(() -> new IngredientNotFound("Ingrediente não encontrado pelo ID: " + id));
    }

    public Ingredient updateIngredient(Long id, Ingredient ingredient) throws IngredientNotFound {
        Ingredient existingIngredient = ingredientRepository.findById(id).orElse(ingredient);

        if (existingIngredient != null) {
            existingIngredient.setName(ingredient.getName());
            existingIngredient.setValidity(ingredient.getValidity());
            existingIngredient.setAmount(ingredient.getAmount());
            return ingredientRepository.save(existingIngredient);
        } else {
            throw new IngredientNotFound("Ingrediente não encontrado pelo ID: " + id);
        }
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}
