package br.com.zup.squad1.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private String name;
    private String preparation;
    private String difficulty;
}


