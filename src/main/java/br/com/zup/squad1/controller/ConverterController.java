package br.com.zup.squad1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import br.com.zup.squad1.dto.ConverterRequestDTO;
import br.com.zup.squad1.dto.ConverterResponseDTO;
import br.com.zup.squad1.service.ConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("converter")
@RequiredArgsConstructor
@Tag(name = "To convert")
public class ConverterController {

    private final ConverterService converterService;
    @PostMapping
    public ResponseEntity<ConverterResponseDTO> converter(@RequestBody @Valid ConverterRequestDTO request) {
        String value_converted = converterService.converterMeasure(request.getValue_to_be_converted(), request.getMeasure(), request.getType_to_be_converted());
        ConverterResponseDTO response = new ConverterResponseDTO();
        response.setConverted_value(value_converted);
        return ResponseEntity.ok(response);
    }
}