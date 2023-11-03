package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.WaterCalculateRequestDTO;
import br.com.zup.squad1.dto.WaterCalculateResponseDTO;
import br.com.zup.squad1.service.WaterCalculateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class WaterCalculateControllerTest {

    @InjectMocks
    private WaterCalculateController waterCalculateController;

    @Mock
    private WaterCalculateService waterCalculateService;

    @Test
    @DisplayName("Testa se o método 'calculateAmountOfWater' retorna status 200(Ok)")
    void calculateAmountOfWater(){
        WaterCalculateRequestDTO requestDTO = new WaterCalculateRequestDTO();
        requestDTO.setWeight(72);

        when(waterCalculateService.calculateAmountOfWater(requestDTO.getWeight()))
                .thenReturn(2.520);

        ResponseEntity<WaterCalculateResponseDTO> response = waterCalculateController.calculateAmountOfWater(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}