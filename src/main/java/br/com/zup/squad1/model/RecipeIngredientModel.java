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

    @Column(nullable = false)
    private Long id_recipe;

    @Column(nullable = false)
    private Long id_ingredient;

    @Column(nullable = false)
    private String ingredientQuantity;
}
