package br.com.zup.squad1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConverterRequestModel {

    @Positive(message = "Quantidade deve ser maior que 0")
    @NotNull
    @JsonProperty(value = "valor")
    private double value_to_be_converted;

    @Min(value = 1)
    @Max(value = 4,message = "Opção de medida inválida")
    @NotNull
    @JsonProperty(value = "medida")
    private int measure;

    @Min(value = 1)
    @Max(value = 3,message = "Opção de conversão inválida")
    @NotNull
    @JsonProperty(value = "tipo convertido")
    private int type_to_be_converted;
}