package br.com.zup.squad1.service;

import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public RecipeModel registerRecipe(RecipeModel recipeModel) {
        return recipeRepository.save(recipeModel);
    }

    public RecipeModel consultRecipe(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public RecipeModel changeRecipe(Long id, RecipeModel newRecipe) {
        if (recipeRepository.existsById(id)) {
            newRecipe.setId(id);
            return recipeRepository.save(newRecipe);
        } else {
            throw new RuntimeException("Receita não encontrada");
        }
    }
}
