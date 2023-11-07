package br.com.zup.squad1.controller;


import br.com.zup.squad1.exceptions.InvalidDateFormatException;
import br.com.zup.squad1.model.WaterConsumptionModel;
import br.com.zup.squad1.service.ValidationService;
import br.com.zup.squad1.service.WaterQuantityHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class WaterQuantityHistoryControllerTest {
    @InjectMocks
    private WaterQuantityHistoryController waterQuantityHistoryController;

    @Mock
    private WaterQuantityHistoryService waterQuantityHistoryService;
    @Mock
    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<WaterConsumptionModel> expectedList = new ArrayList<>();
        when(waterQuantityHistoryService.findAll()).thenReturn(expectedList);
        List<WaterConsumptionModel> result = waterQuantityHistoryController.findAll();
        assertEquals(expectedList, result);
    }

    @Test
    void testFindByData() throws InvalidDateFormatException {
        String validDate = "01/01/2023";
        List<WaterConsumptionModel> mockData = new ArrayList<>();

        when(validationService.isValidDateFormat(validDate)).thenReturn(false);
        when(waterQuantityHistoryService.findByDate(validDate)).thenReturn(mockData);

        ResponseEntity<?> response = waterQuantityHistoryController.findByData(validDate);

        if (mockData.isEmpty()) {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertEquals("Nenhum dado encontrado para a data fornecida.", response.getBody());
        } else {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(mockData, response.getBody());
        }
    }

    @Test
    void testFindLast7Days() throws InvalidDateFormatException {
        String validStartDate = "01/01/2023";
        String validEndDate = "07/01/2023";
        List<WaterConsumptionModel> mockData = new ArrayList<>();

        when(validationService.isValidDateFormat(validStartDate)).thenReturn(false);
        when(validationService.isValidDateFormat(validEndDate)).thenReturn(false);
        when(waterQuantityHistoryService.findLast7Days(validStartDate, validEndDate)).thenReturn(mockData);

        ResponseEntity<?> response = waterQuantityHistoryController.findLast7Days(validStartDate, validEndDate);

        if (mockData.isEmpty()) {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertEquals("Nenhum dado encontrado para a data fornecida.", response.getBody());
        } else {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(mockData, response.getBody());
        }
    }

    @Test
    public void testFindByDataWithValidDateFormat() throws InvalidDateFormatException {
        String validYearMonth = "01/2023";
        List<WaterConsumptionModel> mockData = new ArrayList<>();

        when(validationService.isValidYearMonthFormat(validYearMonth)).thenReturn(true);
        when(waterQuantityHistoryService.findByYearMonth(validYearMonth)).thenReturn(mockData);

        ResponseEntity<?> response = waterQuantityHistoryController.findByYearMonth(validYearMonth);

        if (mockData.isEmpty()) {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertEquals("Nenhum dado encontrado para a data fornecida.", response.getBody());
        } else {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(mockData, response.getBody());
        }
    }

    @Test
    public void testFindByDataWithInvalidDateFormat() {
        when(validationService.isValidDateFormat("2023-01-01")).thenReturn(true);

        assertThrows(InvalidDateFormatException.class, () -> waterQuantityHistoryController.findByData("2023-01-01"));
    }
}