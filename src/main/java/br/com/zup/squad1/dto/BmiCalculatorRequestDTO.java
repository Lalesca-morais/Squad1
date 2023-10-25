package br.com.zup.squad1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BmiCalculatorRequestDTO {
    @NotNull
    private double height;
    @NotNull
    private double weight;
}
