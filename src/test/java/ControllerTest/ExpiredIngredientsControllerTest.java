package ControllerTest;

import br.com.zup.squad1.controller.ExpiredIngredientsController;
import br.com.zup.squad1.dto.IngredientDTO;
import br.com.zup.squad1.model.Ingredient;
import br.com.zup.squad1.model.enums.ProductType;
import br.com.zup.squad1.model.enums.State;
import br.com.zup.squad1.repository.IngredientRepository;
import br.com.zup.squad1.service.ExpiredIngredientsService;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(ExpiredIngredientsController.class)
public class ExpiredIngredientsControllerTest {
    private MockMvc mockMvc;
    @Mock
    IngredientRepository ingredientRepository;
    @Mock
    private ExpiredIngredientsService expiredIngredientsService;
    @InjectMocks
    private ExpiredIngredientsController expiredIngredientsController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(expiredIngredientsController).build();
    }

    @Test
    public void testListExpiredIngredients() {
        LocalDate currentDate = LocalDate.of(2023, 10, 1);

        List<Ingredient> mockIngredients = Arrays.asList(
                new Ingredient(1L, "Banana", LocalDate.of(2023, 1, 10), 10, ProductType.FRUIT, State.GREEN, null),
                new Ingredient(2L, "Mel", LocalDate.of(2023, 2, 10), 20, ProductType.NON_PERISHABLE, null, null));

        when(ingredientRepository.findByExpiredIngredients(currentDate)).thenReturn(mockIngredients);
        List<Ingredient> expiredIngredients = expiredIngredientsService.listExpiredIngredients(currentDate);
        assertEquals(2, expiredIngredients.size());
        assertEquals("Banana", expiredIngredients.get(0).getName());
        assertEquals("Mel", expiredIngredients.get(1).getName());
    }

    @Test
    @DisplayName("Teste para quando a lista de ingredientes expirados está vazia")
    public void testListExpiredIngredients2() {
        LocalDate currentDate = LocalDate.of(2023, 10, 1);

        when(ingredientRepository.findByExpiredIngredients(currentDate)).thenReturn(Collections.emptyList());
        List<IngredientDTO> result = expiredIngredientsController.listExpiredIngredients(currentDate);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Teste para quando a lista de ingredientes expirados contém um único ingrediente")
    public void testListExpiredIngredients3() {
        LocalDate currentDate = LocalDate.of(2023, 10, 1);
        Ingredient mockIngredient = new Ingredient(1L, "Morango", LocalDate.of(2023, 1, 10), 10, ProductType.FRUIT, State.GREEN, null);

        when(ingredientRepository.findByExpiredIngredients(currentDate)).thenReturn(Collections.singletonList(mockIngredient));
        List<IngredientDTO> result = expiredIngredientsController.listExpiredIngredients(currentDate);
        assertEquals(1, result.size());
        IngredientDTO ingredientDTO = result.get(0);
        assertEquals(mockIngredient.getName(), ingredientDTO.getName());
    }

    @Test
    @DisplayName("Teste para quando a lista de ingredientes está com vários ingredientes")
    public void testListExpiredIngredients4() {
        LocalDate currentDate = LocalDate.of(2023, 10, 1);

        List<Ingredient> mockIngredients = Arrays.asList(
                new Ingredient(1L, "Uva", LocalDate.of(2023, 1, 10), 10, ProductType.FRUIT, State.MATURE, null),
                new Ingredient(2L, "Bolo", LocalDate.of(2023, 2, 15), 20, ProductType.BAKERY, null, null),
                new Ingredient(3L, "Café em grãos", LocalDate.of(2023, 4, 7), 20, ProductType.NON_PERISHABLE, null, null),
                new Ingredient(4L, "Croissant", LocalDate.of(2023, 3, 8), 20, ProductType.BAKERY, null, null));

        when(ingredientRepository.findByExpiredIngredients(currentDate)).thenReturn(mockIngredients);
        List<IngredientDTO> result = expiredIngredientsController.listExpiredIngredients(currentDate);
        assertEquals(2, result.size());
    }
}
