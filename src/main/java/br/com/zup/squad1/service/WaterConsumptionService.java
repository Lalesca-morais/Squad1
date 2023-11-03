package br.com.zup.squad1.service;

import br.com.zup.squad1.exceptions.IdealWaterAmountUnregisteredException;
import br.com.zup.squad1.model.WaterConsumptionModel;
import br.com.zup.squad1.model.WaterModel;
import br.com.zup.squad1.repository.WaterConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Service
public class WaterConsumptionService {
    @Autowired
    WaterConsumptionRepository repository;

    @Autowired
    WaterCalculateService waterCalculateService;

    public String registerQuantityIngested(double quantityIngested){
        WaterModel waterModel = waterCalculateService.getExistingWaterModel();
        if(waterModel == null){
            throw new IdealWaterAmountUnregisteredException("Antes de registrar a quantidade de água ingerida, é necessário fazer o cálculo da quantidade ideal.");
        }
        double idealAmount = waterModel.getIdealAmount();
        WaterConsumptionModel waterConsumption = getCurrentWaterConsumption();

        if(waterConsumption != null){
            double currentQuantity = waterConsumption.getQuantity_ingested();
            waterConsumption.setQuantity_ingested(currentQuantity + quantityIngested);
            waterConsumption.setIdeal_amount(idealAmount);
        } else {
            waterConsumption = new WaterConsumptionModel(getCurrentDate(), quantityIngested, idealAmount);
        }

        repository.save(waterConsumption);

        return getGoalInformation(waterConsumption.getQuantity_ingested(), waterConsumption.getIdeal_amount());
    }

    public String getGoalInformation(double quantityIngested, double dailyGoal){
        if(quantityIngested < dailyGoal){
            return "Até o momento, você ingeriu " + quantityIngested + " ml de água. Faltam " + (dailyGoal - quantityIngested) + " ml para você atingir a sua meta diária!";
        } else return "Parabéns! Você atingiu a sua meta diária de ingestão de água! A quantidade total ingerida foi de " + quantityIngested + "ml.";
    }

    public WaterConsumptionModel getCurrentWaterConsumption() {
        Optional<WaterConsumptionModel> currentWaterConsumption = repository.findById(getCurrentDate());
        return currentWaterConsumption.orElse(null);
    }

    public String getCurrentDate(){
        DateTimeFormatter brazilianFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("pt", "BR"));
        return LocalDate.now().format(brazilianFormat);
    }
}
