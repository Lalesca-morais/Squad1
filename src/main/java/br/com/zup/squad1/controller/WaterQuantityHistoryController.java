package br.com.zup.squad1.controller;

import br.com.zup.squad1.exceptions.InvalidDateFormatException;
import br.com.zup.squad1.model.WaterConsumptionModel;
import br.com.zup.squad1.service.ValidationService;
import br.com.zup.squad1.service.WaterQuantityHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historicQuantityWater")
@Tag(name = "Check water consumption history")
public class WaterQuantityHistoryController {

    private final WaterQuantityHistoryService waterQuantityHistoryService;
    private final ValidationService validationService;

    @Autowired
    public WaterQuantityHistoryController(WaterQuantityHistoryService waterQuantityHistoryService, ValidationService validationService) {
        this.waterQuantityHistoryService = waterQuantityHistoryService;
        this.validationService = validationService;
    }

    @GetMapping("/all")
    public List<WaterConsumptionModel> findAll() {
        return waterQuantityHistoryService.findAll();
    }

    @GetMapping("/byData")
    public ResponseEntity<?> findByData(@RequestParam("date") String date) throws InvalidDateFormatException {
        if (validationService.isValidDateFormat(date)) {
            throw new InvalidDateFormatException("Data não está no formato correto (dd/MM/yyyy).");
        }
        List<WaterConsumptionModel> result = waterQuantityHistoryService.findByDate(date);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum dado encontrado para a data fornecida.");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/last7days")
    public ResponseEntity<?> findLast7Days(@RequestParam("dateStart") String dateStart, @RequestParam("endDate") String endDate) throws InvalidDateFormatException {
        if (validationService.isValidDateFormat(dateStart) || validationService.isValidDateFormat(endDate)) {
            throw new InvalidDateFormatException("Data não está no formato correto (dd/MM/yyyy).");
        }
        List<WaterConsumptionModel> result = waterQuantityHistoryService.findLast7Days(dateStart, endDate);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum dado encontrado para a data fornecida.");
        }
        return ResponseEntity.ok(result);

    }

    @GetMapping("/byYearMonth")
    public ResponseEntity<?> findByYearMonth(@RequestParam("yearMonth") String yearMonth) throws InvalidDateFormatException {
        if (!validationService.isValidYearMonthFormat(yearMonth)) {
            throw new InvalidDateFormatException("Ano/mês não está no formato correto (MM/yyyy).");
        }
        List<WaterConsumptionModel> result = waterQuantityHistoryService.findByYearMonth(yearMonth);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum dado encontrado para a data fornecida.");
        }
        return ResponseEntity.ok(result);
    }
}
