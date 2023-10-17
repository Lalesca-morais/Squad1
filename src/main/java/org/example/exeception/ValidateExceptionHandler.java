package org.example.exeception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static java.util.Collections.singletonList;
@RestControllerAdvice
public class ValidateExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidateError> methodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                         HttpServletRequest request) {
        ValidateError errors = new ValidateError();
        for (FieldError field : e.getBindingResult().getFieldErrors()) {
            errors.addError(field.getField(), singletonList(field.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(errors);
    }
}