package br.com.zup.squad1.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientRequestDTO {
    @NotNull
    @JsonProperty("id_receita")
    private Long idRecipe;

    @NotNull
    @JsonProperty("id_ingrediente")
    private Long idIngredient;

    @NotNull
    @JsonProperty("quantidade_ingrediente")
    private String ingredientQuantity;
}
