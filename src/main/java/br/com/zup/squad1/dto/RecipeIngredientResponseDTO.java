package br.com.zup.squad1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientResponseDTO {
    @JsonProperty("id do ingrediente")
    private Long ingredientId;

    @JsonProperty("nome")
    private String ingredientName;

    @JsonProperty("quantidade")
    private String ingredientQuantity;
}
