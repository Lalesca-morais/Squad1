package br.com.zup.squad1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterCalculateResponseDTO {

    @JsonProperty(value = "quantidade_ideal")
    private String idealAmountOfWater;
}
