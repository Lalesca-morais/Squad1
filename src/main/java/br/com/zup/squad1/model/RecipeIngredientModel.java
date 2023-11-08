package br.com.zup.squad1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RecipeIngredient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("id_receita")
    private Long id_recipe;

    @Column(nullable = false)
    @JsonProperty("id_ingrediente")
    private Long id_ingredient;

    @Column(nullable = false)
    @JsonProperty("quantidade_ingrediente")
    private String ingredientQuantity;
}
