package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.ConverterRequestDTO;
import br.com.zup.squad1.dto.ConverterResponseDTO;
import br.com.zup.squad1.service.ConverterService;
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
public class ConverterControllerTest {
    @InjectMocks
    private ConverterController converterController;
    @Mock
    private ConverterService converterService;

    @Test
    @DisplayName("Teste se o metodo 'converter' retorna status 200(Ok)")
    void converter_t1(){
        ConverterRequestDTO requestDTO = new ConverterRequestDTO();
        requestDTO.setValue_to_be_converted(34.5);
        requestDTO.setMeasure(1);
        requestDTO.setType_to_be_converted(2);

        when(converterService.converterMeasure(requestDTO.getValue_to_be_converted(),
                requestDTO.getMeasure(),requestDTO.getType_to_be_converted()))
                .thenReturn("20.0");

        ResponseEntity<?> response = converterController.converter(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    @DisplayName("Teste se o metodo 'converter' retorna status 400(Bad request) com quantidade inválida")
    void converter_t2(){
        ConverterRequestDTO requestDTO = new ConverterRequestDTO();
        requestDTO.setValue_to_be_converted(0);
        requestDTO.setMeasure(1);
        requestDTO.setType_to_be_converted(2);

        ResponseEntity<?> response = converterController.converter(requestDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    @DisplayName("Teste se o metodo 'converter' retorna status 400(Bad request) com medida inválida")
    void converter_t3(){
        ConverterRequestDTO requestDTO = new ConverterRequestDTO();
        requestDTO.setValue_to_be_converted(350);
        requestDTO.setMeasure(8);
        requestDTO.setType_to_be_converted(2);

        ResponseEntity<?> response = converterController.converter(requestDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Teste se o metodo 'converter' retorna status 400(Bad request) com tipo convertido inválido")
    void converter_t4(){
        ConverterRequestDTO requestDTO = new ConverterRequestDTO();
        requestDTO.setValue_to_be_converted(560);
        requestDTO.setMeasure(1);
        requestDTO.setType_to_be_converted(0);

        ResponseEntity<?> response = converterController.converter(requestDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}