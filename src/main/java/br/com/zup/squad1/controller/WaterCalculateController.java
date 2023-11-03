package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.WaterCalculateRequestDTO;
import br.com.zup.squad1.dto.WaterCalculateResponseDTO;
import br.com.zup.squad1.service.WaterCalculateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("calculate-water")
@RequiredArgsConstructor
@Tag(name = "Calculate ideal amount of water")
public class WaterCalculateController {
    @Autowired
    private WaterCalculateService waterCalculateService;
    @PostMapping
    public ResponseEntity<WaterCalculateResponseDTO> calculateAmountOfWater(@RequestBody @Valid WaterCalculateRequestDTO request){
        double idealAmount = waterCalculateService.calculateAmountOfWater(request.getWeight());
        String message = waterCalculateService.formatsIdealWaterQuantityMessage(idealAmount);
        WaterCalculateResponseDTO response = new WaterCalculateResponseDTO();
        response.setIdealAmountOfWater(message);
        return ResponseEntity.ok(response);
    }
}