package br.com.zup.squad1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class RecipeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "RecipeIngredient",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_ingredient"))
    private Set<Ingredient> ingredients;

    @Column(length = 600)
    private String preparation;

    @Column(length = 10)
    private String difficulty;
}



