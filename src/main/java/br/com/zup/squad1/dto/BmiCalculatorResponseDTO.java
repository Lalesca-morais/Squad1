package br.com.zup.squad1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BmiCalculatorResponseDTO {
    private BigDecimal result;
    private String message;
}
