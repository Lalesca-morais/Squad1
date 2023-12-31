package br.com.zup.squad1.dto;

import br.com.zup.squad1.controller.validation.DueDateValidation;
import br.com.zup.squad1.model.enums.State;
import br.com.zup.squad1.model.enums.ProductType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "validade")
    @NotNull(groups = DueDateValidation.class, message = "Data de validade nao deve ser null")
    @Future(groups = WithValidity.class)
    private LocalDate validity;

    @JsonProperty(value = "quantidade")
    @NotNull
    @Positive
    private Double amount;

    @JsonProperty("tipo_produto")
    private ProductType productType;

    @JsonProperty(value = "estado")
    private State state;

    public interface WithValidity {
    }
}
