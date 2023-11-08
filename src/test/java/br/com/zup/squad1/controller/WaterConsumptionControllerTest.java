package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.WaterConsumptionRequestDTO;
import br.com.zup.squad1.dto.WaterConsumptionResponseDTO;
import br.com.zup.squad1.service.WaterConsumptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class WaterConsumptionControllerTest {
    @Mock
    WaterConsumptionService service;

    @InjectMocks
    WaterConsumptionController controller;

    @Test
    public void testRegisterQuantityIngested() {
        WaterConsumptionRequestDTO requestDTO = new WaterConsumptionRequestDTO(500);

        when(service.registerQuantityIngested(500)).thenReturn("Até o momento, você ingeriu 500.0 ml de água. Faltam 2000.0 ml para você atingir a sua meta diária!");

        ResponseEntity<WaterConsumptionResponseDTO> response = controller.registerQuantityIngested(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(service).registerQuantityIngested(500);
    }

}