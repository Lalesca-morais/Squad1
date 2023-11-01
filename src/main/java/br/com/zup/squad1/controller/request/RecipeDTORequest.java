package br.com.zup.squad1.controller.request;

import br.com.zup.squad1.dto.RecipeIngredientRequestDTO;
import jakarta.validation.Valid;
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

    @NotEmpty
    private String name;

    @NotEmpty
    private String preparation;

    @NotEmpty
    private String difficulty;

    @Valid
    private List<@NotNull RecipeIngredientRequestDTO> ingredients;
}


