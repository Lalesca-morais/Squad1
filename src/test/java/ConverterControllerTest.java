import org.example.controller.ConverterController;
import org.example.model.ConverterRequestModel;
import org.example.model.ConverterResponseModel;
import org.example.service.ConverterService;
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
        ConverterRequestModel requestModel = new ConverterRequestModel();
        requestModel.setValue_to_be_converted(34.5);
        requestModel.setMeasure(1);
        requestModel.setType_to_be_converted(2);

        when(converterService.converterMeasure(requestModel.getValue_to_be_converted(),
                requestModel.getMeasure(),requestModel.getType_to_be_converted()))
                .thenReturn("20.0");

        ResponseEntity<ConverterResponseModel> response = converterController.converter(requestModel);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}