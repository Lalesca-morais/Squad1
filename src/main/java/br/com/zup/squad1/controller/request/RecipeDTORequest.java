package br.com.zup.squad1.controller.request;

import br.com.zup.squad1.dto.RecipeIngredientDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTORequest {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String preparation;

    @NotBlank
    private String difficulty;

    @NotNull
    @NotEmpty
    private List<RecipeIngredientDto> ingredients;
}


