package br.com.zup.squad1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConverterRequestModel {

    @JsonProperty(value = "valor")
    private double value_to_be_converted;

    @JsonProperty(value = "medida")
    private int measure;

    @JsonProperty(value = "tipo convertido")
    private int type_to_be_converted;
}