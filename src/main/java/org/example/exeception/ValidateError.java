package org.example.exeception;

import java.util.ArrayList;
import java.util.List;
public class ValidateError {
    private List<FieldMessage> erros = new ArrayList<>();
    public List<FieldMessage> getErros() {
        return erros;
    }
    public void addError(String campo, List<String> messages) {
        erros.add(new FieldMessage(campo, messages));
    }
}