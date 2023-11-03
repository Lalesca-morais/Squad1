package br.com.zup.squad1.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("nome")
    private String name;

    @NotEmpty
    @JsonProperty("preparo")
    private String preparation;

    @NotEmpty
    @JsonProperty("dificuldade")
    private String difficulty;

    @Valid
    @JsonProperty("ingredientes")
    private List<@NotNull RecipeIngredientRequestDTO> ingredients;
}


