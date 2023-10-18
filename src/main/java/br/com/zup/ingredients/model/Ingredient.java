package br.com.zup.ingredients.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

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
    private Set<Recipe> revenues = new HashSet<>();

}
