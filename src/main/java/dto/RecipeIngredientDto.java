package dto;

import lombok.Data;

@Data
public class RecipeIngredientDto{
    private Long idRecipe;
    private Long idIngredient;
    private String ingredientQuantity;
}
