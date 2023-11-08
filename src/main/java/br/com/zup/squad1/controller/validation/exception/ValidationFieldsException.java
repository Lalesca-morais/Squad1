package br.com.zup.squad1.controller.validation.exception;

import br.com.zup.squad1.dto.IngredientDTO;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class ValidationFieldsException extends Exception{
    public ValidationFieldsException(String message) {
        super(message);
    }

    public ValidationFieldsException() {
    }

    private Set<ConstraintViolation<IngredientDTO>> violationSet;
}
