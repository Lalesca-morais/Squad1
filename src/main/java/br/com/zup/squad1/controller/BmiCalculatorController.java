package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.BmiCalculatorRequestDTO;
import br.com.zup.squad1.dto.BmiCalculatorResponseDTO;
import br.com.zup.squad1.service.BmiCalculatorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/imc")
@Tag(name = "IMC")
public class BmiCalculatorController {

    @Autowired
    BmiCalculatorService service;

    @PostMapping
    public ResponseEntity<BmiCalculatorResponseDTO> calculateBmi (@RequestBody @Valid BmiCalculatorRequestDTO requestDTO){
        double result = service.getBmiResult(requestDTO);
        String message = service.getClassification(result);

        BigDecimal bmi = BigDecimal.valueOf(result);
        bmi = bmi.setScale(2, RoundingMode.HALF_UP);

        BmiCalculatorResponseDTO responseDTO = new BmiCalculatorResponseDTO(bmi, message);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
