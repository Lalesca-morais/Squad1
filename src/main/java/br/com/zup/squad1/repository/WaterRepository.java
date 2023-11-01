package br.com.zup.squad1.repository;

import br.com.zup.squad1.model.WaterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterRepository extends JpaRepository<WaterModel, Long> {
}