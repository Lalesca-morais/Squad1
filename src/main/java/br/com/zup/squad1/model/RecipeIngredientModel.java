package br.com.zup.squad1.model;

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

    @ManyToOne
    @JoinColumn(name = "id_recipe")
    private RecipeModel recipe;

    @ManyToOne
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    @Column(nullable = false)
    private String ingredientQuantity;
}
