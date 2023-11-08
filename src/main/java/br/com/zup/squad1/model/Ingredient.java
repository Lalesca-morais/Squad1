package br.com.zup.squad1.model;

import br.com.zup.squad1.model.enums.ProductType;
import br.com.zup.squad1.model.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "validity")
    private LocalDate validity;

    @Column(name = "amount")
    private double amount;

    @Column(name = "productType")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    public Ingredient(Long id, String name) {

    }

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private Set<RecipeModel> recipes = new HashSet<>();

}

