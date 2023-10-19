package com.example.squad1.controllers.controller.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRequest {
    private String name;
    private Integer idPrepareRequest;
    private Integer idDifficultyRequest;
    private Integer idIngredientRequest;
}


