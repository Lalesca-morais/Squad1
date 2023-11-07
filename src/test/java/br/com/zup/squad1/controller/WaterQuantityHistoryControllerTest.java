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
        List<WaterConsumptionModel> expectedList = new ArrayList<>();
        String date = "2023-11-07";
        when(waterQuantityHistoryService.findByDate(date)).thenReturn(expectedList);
        List<WaterConsumptionModel> result = waterQuantityHistoryController.findByData(date);
        assertEquals(expectedList, result);
    }

    @Test
    void testFindLast7Days() throws InvalidDateFormatException {
        List<WaterConsumptionModel> expectedList = new ArrayList<>();
        String dateStart = "2023-11-01";
        String endDate = "2023-11-07";
        when(waterQuantityHistoryService.findLast7Days(dateStart, endDate)).thenReturn(expectedList);
        List<WaterConsumptionModel> result = waterQuantityHistoryController.findLast7Days(dateStart, endDate);
        assertEquals(expectedList, result);
    }

    @Test
    public void testFindByDataWithValidDateFormat() throws InvalidDateFormatException {
        when(validationService.isValidDateFormat("01/01/2023")).thenReturn(false);
        List<WaterConsumptionModel> expectedList = new ArrayList();
        when(waterQuantityHistoryService.findByDate("01/01/2023")).thenReturn(expectedList);

        List<WaterConsumptionModel> result = waterQuantityHistoryController.findByData("01/01/2023");

        assertSame(expectedList, result);
    }

    @Test
    public void testFindByDataWithInvalidDateFormat() {
        when(validationService.isValidDateFormat("2023-01-01")).thenReturn(true);

        assertThrows(InvalidDateFormatException.class, () -> waterQuantityHistoryController.findByData("2023-01-01"));
    }
}
