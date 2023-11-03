package br.com.zup.squad1.dto;


import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private Long id;

    private String name;

    private String preparation;

    private String difficulty;

    private List<RecipeIngredientDto> ingredients;
}


