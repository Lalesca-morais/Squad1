package br.com.zup.squad1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterConsumptionResponseDTO {
    @JsonProperty(value = "meta")
    private String daily_goal;
}
