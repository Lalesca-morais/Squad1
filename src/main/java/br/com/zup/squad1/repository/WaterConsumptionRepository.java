package br.com.zup.squad1.repository;

import br.com.zup.squad1.model.WaterConsumptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WaterConsumptionRepository extends JpaRepository<WaterConsumptionModel, String> {

    List<WaterConsumptionModel> findByDate(String date);
    List<WaterConsumptionModel> findByDateBetween(String startDate, String endDate);
    @Query("SELECT e FROM WaterConsumptionModel e WHERE SUBSTRING(e.date, 4, 7) = :yearMonth")
    List<WaterConsumptionModel> findByDateStartingWith(String yearMonth);
    List<WaterConsumptionModel> findAll();
}
