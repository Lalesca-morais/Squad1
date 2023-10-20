package br.com.zup.squad1.dto;

import br.com.zup.squad1.model.enums.State;
import br.com.zup.squad1.model.enums.ProductType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private Long id;

    @NotBlank
    private String name;

    @Future(groups = WithValidity.class)
    private LocalDate validity;

    @NotNull
    private double amount;

    private ProductType productType;
    private State state;

    public interface WithValidity {
    }
}
