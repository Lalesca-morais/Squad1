package com.example.squad1.services.service;


import com.example.squad1.controllers.controller.request.IngredientRequest;
import com.example.squad1.models.model.IngredientModel;
import com.example.squad1.repositories.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public IngredientModel createIngredient(IngredientRequest ingredientRequest) {
        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setName(ingredientRequest.getNameRequest());
        return ingredientRepository.save(ingredientModel);
    }

    public IngredientModel getIngredient(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    public IngredientModel updateIngredient(Long id, IngredientModel updatedIngredient) {
        if (ingredientRepository.existsById(id)) {
            updatedIngredient.setId(id);
            return ingredientRepository.save(updatedIngredient);
        } else {
            throw new RuntimeException("Ingrediente não encontrado");
        }
    }
}
