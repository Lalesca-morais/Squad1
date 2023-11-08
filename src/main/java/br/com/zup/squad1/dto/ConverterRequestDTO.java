package br.com.zup.squad1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
@Data
public class ConverterRequestDTO {

    @JsonProperty(value = "valor")
    @Positive(message = "Quantidade inválida, quantidade deve ser maior que zero")
    private double value_to_be_converted;

    @JsonProperty(value = "medida")
    @Min(value = 1, message = "Medida inválida, digite uma medida que esteja entre 1 e 4")
    @Max(value = 4, message = "Medida inválida, digite uma medida que esteja entre 1 e 4")
    private int measure;

    @JsonProperty(value = "tipo_convertido")
    @Min(value = 1, message = "Tipo a ser convertido inválido, digite um tipo que esteja entre 1 e 3")
    @Max(value = 3, message = "Tipo a ser convertido inválido, digite um tipo que esteja entre 1 e 3")
    private int type_to_be_converted;
}