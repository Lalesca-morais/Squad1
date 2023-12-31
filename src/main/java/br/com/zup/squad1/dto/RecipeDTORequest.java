package br.com.zup.squad1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
}


