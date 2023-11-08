package br.com.zup.squad1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WaterConsumption")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterConsumptionModel {
    @Id
    @JsonProperty("data")
    private String date;

    @Column(nullable = false)
    @JsonProperty("quantidade ingerida")
    private double quantity_ingested;

    @Column(nullable = false)
    @JsonProperty("quantidade ideal")
    private double ideal_amount;
}
