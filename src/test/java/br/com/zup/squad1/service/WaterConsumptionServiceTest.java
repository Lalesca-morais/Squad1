package br.com.zup.squad1.service;

import br.com.zup.squad1.exceptions.IdealWaterAmountUnregisteredException;
import br.com.zup.squad1.model.WaterConsumptionModel;
import br.com.zup.squad1.model.WaterModel;
import br.com.zup.squad1.repository.WaterConsumptionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WaterConsumptionServiceTest {
    @Mock
    WaterConsumptionRepository repository;

    @Mock
    WaterCalculateService waterCalculateService;

    @InjectMocks
    WaterConsumptionService service;

    @Test
    @DisplayName("Deve lançar exceção quando WaterModel é nulo")
    void registerQuantityIngestedException() {
        when(waterCalculateService.getExistingWaterModel()).thenReturn(null);
        assertThrows(IdealWaterAmountUnregisteredException.class, () -> service.registerQuantityIngested(200));
    }

    @Test
    @DisplayName("Deve registrar a quantidade ingerida atualizando o registro existente")
    void registerQuantityIngestedUpdate() {
        WaterModel waterModel = new WaterModel(1L,2500);
        WaterConsumptionModel waterConsumption = new WaterConsumptionModel(service.getCurrentDate(), 1000, 2500);

        when(waterCalculateService.getExistingWaterModel()).thenReturn(waterModel);
        when(repository.findById(service.getCurrentDate())).thenReturn(Optional.of(waterConsumption));
        String result = service.registerQuantityIngested(500);

        verify(repository).save(waterConsumption);
        assertEquals(waterConsumption.getQuantity_ingested(), 1500);
        assertEquals(waterConsumption.getIdeal_amount(), 2500);
        assertEquals(result, "Até o momento, você ingeriu 1500.0 ml de água. Faltam 1000.0 ml para você atingir a sua meta diária!");
    }

    @Test
    @DisplayName("Deve registrar a quantidade de água ingerida criando um novo registro")
    void registerQuantityIngestedCreate() {
        WaterModel waterModel = new WaterModel(1L, 2500);

        when(waterCalculateService.getExistingWaterModel()).thenReturn(waterModel);
        when(repository.findById(service.getCurrentDate())).thenReturn(Optional.empty());
        String result = service.registerQuantityIngested(500);

        WaterConsumptionModel waterConsumption = new WaterConsumptionModel(service.getCurrentDate(), 500, 2500);
        verify(repository).save(waterConsumption);
        assertEquals(result, "Até o momento, você ingeriu 500.0 ml de água. Faltam 2000.0 ml para você atingir a sua meta diária!");
    }

    @Test
    @DisplayName("Deve retornar mensagem parabenizando o usuário por ter atingido a meta")
    void registerQuantityCongratulations() {
        WaterModel waterModel = new WaterModel(1L, 2500);
        WaterConsumptionModel waterConsumption = new WaterConsumptionModel(service.getCurrentDate(), 2000, 2500);

        when(waterCalculateService.getExistingWaterModel()).thenReturn(waterModel);
        when(repository.findById(service.getCurrentDate())).thenReturn(Optional.of(waterConsumption));
        String result = service.registerQuantityIngested(500);

        verify(repository).save(waterConsumption);
        assertEquals(waterConsumption.getQuantity_ingested(), 2500);
        assertEquals(waterConsumption.getIdeal_amount(), 2500);
        assertEquals(result, "Parabéns! Você atingiu a sua meta diária de ingestão de água! A quantidade total ingerida foi de 2500.0ml.");
    }
}