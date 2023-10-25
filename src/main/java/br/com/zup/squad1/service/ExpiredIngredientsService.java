package br.com.zup.squad1.service;

import br.com.zup.squad1.model.Ingredient;
import br.com.zup.squad1.repository.IngredientRepository;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
@Data
@Service
public class ExpiredIngredientsService {

    @Autowired
    private final IngredientRepository ingredientRepository;
    public ExpiredIngredientsService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    public List<Ingredient> listExpiredIngredients(LocalDate currentDate) {
        return ingredientRepository.findByExpiredIngredients(currentDate);
    }
}

