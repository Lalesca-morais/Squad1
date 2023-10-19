package br.com.zup.squad1.controller.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequest {
    private String nameRequest;
}
