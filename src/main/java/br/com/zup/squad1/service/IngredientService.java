package br.com.zup.squad1.service;

import br.com.zup.squad1.exceptions.IngredientNotFound;
import br.com.zup.squad1.model.Ingredient;
import br.com.zup.squad1.model.enums.ProductType;
import br.com.zup.squad1.model.enums.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.squad1.repository.IngredientRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredient.getProductType() == ProductType.NON_PERISHABLE && ingredient.getValidity() == null) {
            LocalDate dueDate = calculateDueDate(ingredient);
            ingredient.setValidity(dueDate);
        }
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> listAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient listIngredientById(Long id) throws IngredientNotFound {
        return ingredientRepository.findById(id).orElseThrow(() -> new IngredientNotFound("Ingrediente não encontrado pelo ID: " + id));
    }

    public Ingredient updateIngredient(Long id, Ingredient ingredient) throws IngredientNotFound {
        Optional<Ingredient> existingIngredient = ingredientRepository.findById(id);

        if (existingIngredient.isPresent()) {
            Ingredient updatedIngredient = existingIngredient.get();
            updatedIngredient.setName(ingredient.getName());
            updatedIngredient.setValidity(ingredient.getValidity());
            updatedIngredient.setAmount(ingredient.getAmount());
            return ingredientRepository.save(updatedIngredient);
        } else {
            throw new IngredientNotFound("Ingrediente não encontrado pelo ID: " + id);
        }
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    public LocalDate calculateDueDate(Ingredient ingredient) {
        LocalDate validityDate = LocalDate.now();

        if (ingredient.getProductType() == ProductType.FRUIT) {
            State state = ingredient.getState();
            if (state != null) {
                switch (state) {
                    case GREEN:
                        validityDate = validityDate.plus(15, ChronoUnit.DAYS);
                        break;
                    case ALMOST_MATURE:
                        validityDate = validityDate.plus(7, ChronoUnit.DAYS);
                        break;
                    case MATURE:
                        validityDate = validityDate.plus(3, ChronoUnit.DAYS);
                        break;
                }
            }
        } else if (ingredient.getProductType() == ProductType.BAKERY) {
            validityDate = validityDate.plus(1, ChronoUnit.DAYS);
        } else if (ingredient.getProductType() == ProductType.NON_PERISHABLE) {
            validityDate = LocalDate.now().plus(20, ChronoUnit.YEARS);
        }
        return validityDate;
    }
}


