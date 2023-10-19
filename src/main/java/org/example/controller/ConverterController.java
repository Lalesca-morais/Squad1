package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ConverterRequestDTO;
import org.example.dto.ConverterResponseDTO;
import org.example.model.ConverterRequestModel;
import org.example.model.ConverterResponseModel;
import org.example.service.ConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("converter")
@RequiredArgsConstructor
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