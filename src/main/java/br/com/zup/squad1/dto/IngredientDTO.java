package br.com.zup.squad1.dto;

import br.com.zup.squad1.model.enums.State;
import br.com.zup.squad1.model.enums.ProductType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull
    private String name;

    @Future(groups = WithValidity.class)
    private LocalDate validity;

    @NotNull
    @Positive
    private Double amount;

    private ProductType productType;
    private State state;

    public interface WithValidity {
    }
}
