package br.com.zup.squad1.controller.validation;

import br.com.zup.squad1.controller.validation.exception.ValidationFieldsException;
import br.com.zup.squad1.dto.IngredientDTO;
import br.com.zup.squad1.model.enums.ProductType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Set;

public class Validations {
    private final Validator validator;

    private final IngredientDTO ingredientDTO;

    public Validations(Validator validator, IngredientDTO ingredientDTO) {
        this.validator = validator;
        this.ingredientDTO = ingredientDTO;
    }

    public void validationRequest() throws ValidationFieldsException {
        dueDateValidation();
        validationProductType();
    }

    private void validationProductType() throws ValidationFieldsException {
        try{
            ProductType.valueOf(ingredientDTO.getProductType().name());
        }catch (Exception e){
            throw new ValidationFieldsException("Valor inválido para tipo do produto");
        }
    }

    private void dueDateValidation() throws ValidationFieldsException {
        if (ingredientDTO.getProductType() == null){
            Set<ConstraintViolation<IngredientDTO>> violations =
                    validator.validate(ingredientDTO, DueDateValidation.class);
            checkFieldErros(violations);
        }
    }

    private void checkFieldErros(Set<ConstraintViolation<IngredientDTO>> violations) throws ValidationFieldsException {
        if (violations.isEmpty()) return;
        ValidationFieldsException exception = new ValidationFieldsException();
        exception.setViolationSet(violations);
        throw exception;
    }
}
