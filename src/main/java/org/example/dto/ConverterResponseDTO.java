package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class ConverterResponseDTO {
    @JsonProperty(value = "convertido")
    private String converted_value;
}