package br.com.zup.squad1.exceptions;

import br.com.zup.squad1.controller.validation.exception.ValidationFieldsException;
import br.com.zup.squad1.dto.IngredientDTO;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(IngredientNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleIngredientNotFoundException(IngredientNotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationFieldsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<List<String>> fieldsValidation(ValidationFieldsException exception){

        Set<ConstraintViolation<IngredientDTO>> violationSet = exception.getViolationSet();

        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<IngredientDTO>violation : violationSet ){
            errors.add(violation.getMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }
}