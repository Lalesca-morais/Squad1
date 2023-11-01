package br.com.zup.squad1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientResponseDTO {
    private Long ingredientId;
    private String ingredientName;
    private String ingredientQuantity;
}
