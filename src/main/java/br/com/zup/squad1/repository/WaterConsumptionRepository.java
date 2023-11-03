package br.com.zup.squad1.repository;

import br.com.zup.squad1.model.WaterConsumptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterConsumptionRepository extends JpaRepository<WaterConsumptionModel, String> {
}
