package com.example.squad1.dto.DTO;

import lombok.*;

import java.util.Set;

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


