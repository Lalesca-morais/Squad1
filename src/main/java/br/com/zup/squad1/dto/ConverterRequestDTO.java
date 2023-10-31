package br.com.zup.squad1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class ConverterRequestDTO {

    @JsonProperty(value = "valor")
    private double value_to_be_converted;


    @JsonProperty(value = "medida")
    private int measure;


    @JsonProperty(value = "tipo convertido")
    private int type_to_be_converted;
}