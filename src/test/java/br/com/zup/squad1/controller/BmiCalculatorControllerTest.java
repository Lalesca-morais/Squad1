package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.BmiCalculatorRequestDTO;
import br.com.zup.squad1.dto.BmiCalculatorResponseDTO;
import br.com.zup.squad1.service.BmiCalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BmiCalculatorControllerTest {
    @Mock
    private BmiCalculatorService service;

    @InjectMocks
    private BmiCalculatorController controller;

    @Test
    @DisplayName("Deve retornar o resultado do imc juntamente com a mensagem da classificação com sucesso")
    public void calculateBmi() {
        BmiCalculatorRequestDTO requestDTO = new BmiCalculatorRequestDTO(1.60, 50.0);

        double result = 19.53;
        String message = "Peso normal";

        BmiCalculatorResponseDTO responseDTO = new BmiCalculatorResponseDTO(BigDecimal.valueOf(result), message);

        when(service.getBmiResult(requestDTO)).thenReturn(result);
        when(service.getClassification(result)).thenReturn(message);

        ResponseEntity<Object> responseEntity = controller.calculateBmi(requestDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    @DisplayName("Deve retornar que os valores dos campos devem ser maior que 0")
    public void calculateBmiError() {
        BmiCalculatorRequestDTO requestDTO = new BmiCalculatorRequestDTO(1.60, -50.0);

        ResponseEntity<Object> response = controller.calculateBmi(requestDTO);

        String message = "Os campos de peso e altura devem ser preenchidos com um valor decimal maior que 0.";

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(message, response.getBody());
    }
}