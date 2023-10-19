package br.com.zup.squad1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConverterResponseModel {
    @JsonProperty(value = "convertido")
    private String converted_value;
}