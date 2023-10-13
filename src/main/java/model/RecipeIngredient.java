package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RecipeIngredient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_recipe")
    @Column(nullable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "id_ingredient")
    @Column(nullable = false)
    private Ingredient ingredient;
}
