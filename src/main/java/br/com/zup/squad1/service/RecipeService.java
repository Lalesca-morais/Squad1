package br.com.zup.squad1.service;

import br.com.zup.squad1.exceptions.RecipeNotFoundException;
import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeModel registerRecipe(RecipeModel recipeModel) {
        if (recipeModel.getId() != null) {
            throw new IllegalArgumentException("ID não deve ser fornecido ao criar uma nova receita.");
        }
        return recipeRepository.save(recipeModel);
    }

    public RecipeModel consultRecipe(Long id) {
        Optional<RecipeModel> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return recipe.get();
        } else {
            throw new RecipeNotFoundException("Receita não encontrada para o ID: " + id);
        }
    }

    public void deleteRecipe(Long id) {
        try {
            recipeRepository.deleteById(id);
            System.out.println("Receita deletada com sucesso!");
        } catch (EmptyResultDataAccessException e) {
            throw new RecipeNotFoundException("Não foi possível excluir. Receita não encontrada para o ID: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir a receita com o ID: " + id, e);
        }
    }

    public RecipeModel changeRecipe(Long id, RecipeModel newRecipe) {
        if (recipeRepository.existsById(id)) {
            if (!id.equals(newRecipe.getId())) {
                throw new IllegalArgumentException("ID na URL não corresponde ao ID na receita.");}

            System.out.println("Receita atualizada com sucesso!");
            return recipeRepository.save(newRecipe);
        } else {
            throw new RuntimeException("Receita não encontrada.");
        }
    }
}
