package br.com.zup.squad1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BmiCalculatorResponseDTO {
    @JsonProperty(value = "resultado")
    private BigDecimal result;
    @JsonProperty(value = "classificação")
    private String message;
}
