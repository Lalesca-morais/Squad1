package br.com.zup.squad1.model;

import br.com.zup.squad1.model.enums.ProductType;
import br.com.zup.squad1.model.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("nome")
    private String name;

    @Column(name = "validity")
    @JsonProperty("validade")
    private LocalDate validity;

    @Column(name = "amount")
    @JsonProperty("quantidade")
    private double amount;

    @Column(name = "productType")
    @JsonProperty("tipo do produto")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "state")
    @JsonProperty("estado")
    @Enumerated(EnumType.STRING)
    private State state;

    public Ingredient(Long id, String name) {

    }

}

