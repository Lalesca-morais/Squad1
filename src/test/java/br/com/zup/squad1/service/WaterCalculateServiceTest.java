package br.com.zup.squad1.service;

import br.com.zup.squad1.model.WaterModel;
import br.com.zup.squad1.repository.WaterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class WaterCalculateServiceTest {

    @InjectMocks
    private WaterCalculateService waterCalculateService;

    @Mock
    private WaterRepository waterRepository;

    @Mock
    private WaterModel waterModel;

    @Test
    @DisplayName("Testa se o método 'calculateAmountOfWater' retorna o peso")
    void calculateAmountOfWater(){
        Mockito.when(waterRepository.findById(1L)).thenReturn(Optional.of(waterModel));

        double result = waterCalculateService.calculateAmountOfWater(72);
        assertEquals(2520,result);
    }

    @Test
    @DisplayName("Testa se o método 'formatsIdealWaterQuantityMessage' retorna a string")
    void formatsIdealWaterQuantityMessage(){
        String result = waterCalculateService.formatsIdealWaterQuantityMessage(1610);

        assertEquals("1 litro(s) e 610 ml por dia",result);
    }
}