package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.WaterConsumptionRequestDTO;
import br.com.zup.squad1.dto.WaterConsumptionResponseDTO;
import br.com.zup.squad1.service.WaterConsumptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("water-consumption")
@Tag(name = "Daily Water Consumption")
public class WaterConsumptionController {

    @Autowired
    WaterConsumptionService service;

    @PostMapping
    public ResponseEntity<WaterConsumptionResponseDTO> registerQuantityIngested(@RequestBody @Valid WaterConsumptionRequestDTO requestDTO){
        String message = service.registerQuantityIngested(requestDTO.getQuantity_ingested());
        WaterConsumptionResponseDTO responseDTO = new WaterConsumptionResponseDTO(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
