package br.com.zup.squad1.controller;

import br.com.zup.squad1.dto.IngredientDTO;
import br.com.zup.squad1.model.Ingredient;
import br.com.zup.squad1.model.enums.ProductType;
import br.com.zup.squad1.model.enums.State;
import br.com.zup.squad1.service.ExpiredIngredientsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpiredIngredientsControllerTest {
    LocalDate currentDate = LocalDate.now();
    @Mock
    private ExpiredIngredientsService expiredIngredientsService;
    @InjectMocks
    private ExpiredIngredientsController expiredIngredientsController;

    @Test
    public void testListExpiredIngredients() {
        List<Ingredient> mockIngredients = Arrays.asList(
                new Ingredient(1L, "Banana", LocalDate.of(2023, 1, 10), 10, ProductType.FRUIT, State.GREEN),
                new Ingredient(2L, "Mel", LocalDate.of(2023, 2, 10), 20, ProductType.NON_PERISHABLE, null));

        when(expiredIngredientsService.listExpiredIngredients(currentDate)).thenReturn(mockIngredients);
        List<IngredientDTO> result = expiredIngredientsController.listExpiredIngredients(null);
        assertEquals(2, result.size());
        assertEquals("Banana", result.get(0).getName());
        assertEquals("Mel", result.get(1).getName());
    }

    @Test
    @DisplayName("Teste para quando a lista de ingredientes expirados está vazia")
    public void testListExpiredIngredients2() {
        List<Ingredient> emptyList = new ArrayList<>();

        when(expiredIngredientsService.listExpiredIngredients(currentDate)).thenReturn(emptyList);
        List<IngredientDTO> result = expiredIngredientsController.listExpiredIngredients(null);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Teste para quando a lista de ingredientes expirados contém um único ingrediente")
    public void testListExpiredIngredients3() {
        List<Ingredient> mockIngredients = List.of(new Ingredient(1L, "Morango", LocalDate.of(2023, 1, 10), 10, ProductType.FRUIT, State.GREEN));

        when(expiredIngredientsService.listExpiredIngredients(currentDate)).thenReturn(mockIngredients);
        List<IngredientDTO> result = expiredIngredientsController.listExpiredIngredients(null);
        assertEquals(1, result.size());
        IngredientDTO ingredientDTO = result.get(0);
        assertEquals(mockIngredients.get(0).getName(), ingredientDTO.getName());
    }

    @Test
    @DisplayName("Teste para quando a lista de ingredientes está com vários ingredientes")
    public void testListExpiredIngredients4() {
        List<Ingredient> mockIngredients = Arrays.asList(
                new Ingredient(1L, "Uva", LocalDate.of(2023, 1, 10), 10, ProductType.FRUIT, State.MATURE),
                new Ingredient(2L, "Bolo", LocalDate.of(2023, 2, 15), 20, ProductType.BAKERY, null),
                new Ingredient(3L, "Café em grãos", LocalDate.of(2023, 4, 7), 20, ProductType.NON_PERISHABLE, null),
                new Ingredient(4L, "Croissant", LocalDate.of(2023, 3, 8), 20, ProductType.BAKERY, null));

        when(expiredIngredientsService.listExpiredIngredients(currentDate)).thenReturn(mockIngredients);
        List<IngredientDTO> result = expiredIngredientsController.listExpiredIngredients(null);
        assertEquals(4, result.size());
    }
}
