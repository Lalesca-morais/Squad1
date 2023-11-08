package br.com.zup.squad1.service;

import br.com.zup.squad1.model.WaterConsumptionModel;
import br.com.zup.squad1.repository.WaterConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WaterQuantityHistoryService {
    private final WaterConsumptionRepository waterConsumptionRepository;

    @Autowired
    public WaterQuantityHistoryService(WaterConsumptionRepository waterConsumptionRepository) {
        this.waterConsumptionRepository = waterConsumptionRepository;
    }

    public List<WaterConsumptionModel> findByDate(String specificDate) {
        return waterConsumptionRepository.findByDate(specificDate);
    }

    public List<WaterConsumptionModel> findLast7Days(String dateStart, String endDate) {
        return waterConsumptionRepository.findByDateBetween(dateStart, endDate);
    }

    public List<WaterConsumptionModel> findByYearMonth(String yearMonth) {
        return waterConsumptionRepository.findByDateStartingWith(yearMonth);
    }

    public List<WaterConsumptionModel> findAll() {
        return waterConsumptionRepository.findAll();
    }

}
