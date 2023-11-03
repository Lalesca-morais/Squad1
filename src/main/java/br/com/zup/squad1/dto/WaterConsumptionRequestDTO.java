package br.com.zup.squad1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterConsumptionRequestDTO {
    @JsonProperty(value = "quantidade_ingerida")
    @Positive(message = "A quantidade deve ser maior que zero")
    private double quantity_ingested;
}
