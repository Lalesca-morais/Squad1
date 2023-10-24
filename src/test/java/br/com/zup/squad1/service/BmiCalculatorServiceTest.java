package br.com.zup.squad1.service;

import br.com.zup.squad1.dto.BmiCalculatorRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class BmiCalculatorServiceTest {

    private final BmiCalculatorService service = new BmiCalculatorService();

    @Test
    @DisplayName("Deve retornar que o peso está abaixo do normal")
    public void classificationUnderweight(){
        assertEquals("Abaixo do peso normal", service.getClassification(18.4));
    }

    @Test
    @DisplayName("Deve retornar que o peso está normal")
    public void classificationNormalWeight(){
        assertEquals("Peso normal", service.getClassification(18.5));
        assertEquals("Peso normal", service.getClassification(24.9));
    }

    @Test
    @DisplayName("Deve retornar excesso de peso")
    public void classificationOverweight(){
        assertEquals("Excesso de peso", service.getClassification(25.0));
        assertEquals("Excesso de peso", service.getClassification(29.9));
    }

    @Test
    @DisplayName("Deve retornar obesidade classe I")
    public void classificationObesityClass1(){
        assertEquals("Obesidade Classe I", service.getClassification(30.0));
        assertEquals("Obesidade Classe I", service.getClassification(34.9));
    }

    @Test
    @DisplayName("Deve retornar obesidade classe II")
    public void classificationObesityClass2(){
        assertEquals("Obesidade Classe II", service.getClassification(35.0));
        assertEquals("Obesidade Classe II", service.getClassification(39.9));
    }

    @Test
    @DisplayName("Deve retornar obesidade classe III")
    public void classificationObesityClass3(){
        assertEquals("Obesidade Classe III", service.getClassification(40.0));
    }

    @Test
    @DisplayName("Deve retornar o resultado do calculo corretamente")
    public void getBmiResult() {
        BmiCalculatorRequestDTO requestDTO = new BmiCalculatorRequestDTO();
        requestDTO.setHeight(1.8);
        requestDTO.setWeight(70);

        BigDecimal result = BigDecimal.valueOf(service.getBmiResult(requestDTO));
        result = result.setScale(2, RoundingMode.HALF_UP);

        assertEquals(BigDecimal.valueOf(21.60).setScale(2, RoundingMode.HALF_UP),result);
    }
}