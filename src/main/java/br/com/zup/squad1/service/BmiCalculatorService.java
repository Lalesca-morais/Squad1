package br.com.zup.squad1.service;

import br.com.zup.squad1.dto.BmiCalculatorRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class BmiCalculatorService {
    public String getClassification(double bmi){
        if(bmi < 18.5){
            return "Abaixo do peso normal";
        } else if (bmi >= 18.5 && bmi <= 24.9){
            return "Peso normal";
        } else if (bmi >= 25.0 && bmi <= 29.9){
            return "Excesso de peso";
        } else if (bmi >= 30.0 && bmi <= 34.9){
            return "Obesidade Classe I";
        } else if (bmi >= 35.0 && bmi <= 39.9) {
            return "Obesidade Classe II";
        } else return "Obesidade Classe III";
    }

    public double getBmiResult(BmiCalculatorRequestDTO requestDTO){
        return requestDTO.getWeight() / (requestDTO.getHeight() * requestDTO.getHeight());
    }
}
