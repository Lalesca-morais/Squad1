import br.com.zup.squad1.controller.ConverterController;
import br.com.zup.squad1.dto.ConverterRequestDTO;
import br.com.zup.squad1.dto.ConverterResponseDTO;
import br.com.zup.squad1.model.ConverterRequestModel;
import br.com.zup.squad1.model.ConverterResponseModel;
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

        ResponseEntity<ConverterResponseDTO> response = converterController.converter(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}