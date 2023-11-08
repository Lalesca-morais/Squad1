package br.com.zup.squad1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "water")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WaterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ideal_amount")
    private double idealAmount;
}