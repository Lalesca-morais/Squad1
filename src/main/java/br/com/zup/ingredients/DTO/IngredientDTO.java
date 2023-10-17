package br.com.zup.ingredients.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private Long id;
    private String name;
    private LocalDate validity;
    private double amount;

}
