package br.com.zup.squad1.model;

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

    public Ingredient(Long id, String name) {

    }

    @ManyToMany(mappedBy = "ingredients")
    private Set<RecipeModel> recips = new HashSet<>();

}
