package br.com.zup.squad1.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private Long id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("preparo")
    private String preparation;

    @JsonProperty("dificuldade")
    private String difficulty;

    @JsonProperty("ingredientes")
    private List<RecipeIngredientResponseDTO> ingredients;
}


