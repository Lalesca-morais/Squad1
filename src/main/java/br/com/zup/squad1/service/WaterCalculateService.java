package br.com.zup.squad1.service;

import br.com.zup.squad1.model.WaterModel;
import br.com.zup.squad1.repository.WaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class WaterCalculateService {
    @Autowired
    WaterRepository waterRepository;
    public double calculateAmountOfWater(double weight){
        double idealAmount = weight * 35;
        saveIdealAmount(idealAmount);
        return idealAmount;
    }
    public void saveIdealAmount(double idealAmount){
        WaterModel existingWaterModel = getExistingWaterModel();
        if (existingWaterModel != null){
            existingWaterModel.setIdealAmount(idealAmount);
            waterRepository.save(existingWaterModel);
        }else{
            WaterModel waterModel = new WaterModel();
            waterModel.setIdealAmount(idealAmount);
            waterRepository.save(waterModel);
        }
    }
    public String formatsIdealWaterQuantityMessage(double idealAmount){
        int litro = (int) idealAmount / 1000;
        int ml = (int) idealAmount % 1000;
        return litro+" litro(s) "+"e "+ml+" ml por dia";
    }
    public WaterModel getExistingWaterModel() {
        Optional<WaterModel> existingWaterModelOptional = waterRepository.findById(1L);

        return existingWaterModelOptional.orElse(null);
    }
}