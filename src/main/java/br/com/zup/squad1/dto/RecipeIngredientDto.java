package br.com.zup.squad1.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientDto{
    @NotNull
    private Long idRecipe;
    @NotNull
    private Long idIngredient;
    @NotNull
    private String ingredientQuantity;
}
