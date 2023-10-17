package ServiceTest;

import br.com.zup.ingredients.exceptions.IngredientNotFound;
import br.com.zup.ingredients.model.Ingredient;
import br.com.zup.ingredients.repository.IngredientRepository;
import br.com.zup.ingredients.service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IngredientServiceTest {
    @InjectMocks
    private IngredientService ingredientService;

    @Mock
    private IngredientRepository ingredientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mustRegisterCustomerSuccessfully() {
        Ingredient ingredient = new Ingredient(1L, "cenoura", new Date(2023, 10, 19), 4);

        when(ingredientRepository.save(ArgumentMatchers.any(Ingredient.class))).thenReturn(ingredient);

        Ingredient resultRegister = ingredientService.addIngredient(ingredient);

        verify(ingredientRepository, times(1)).save(ArgumentMatchers.any(Ingredient.class));

        assertEquals(ingredient, resultRegister);
    }

    @Test
    public void shouldReturnListOfIngredients() {
        Ingredient ingredient1 = new Ingredient(1L, "cenoura");

        Ingredient ingredient2 = new Ingredient(2L, "açucar");

        Ingredient ingredient3 = new Ingredient(3L, "leite");

        List<Ingredient> ingredients = Arrays.asList(ingredient1, ingredient2, ingredient3);

        when(ingredientRepository.findAll()).thenReturn(ingredients);

        List<Ingredient> result = ingredientService.listAllIngredients();

        verify(ingredientRepository, times(1)).findAll();

        System.out.println("Ingredientes cadastrados");
        for (Ingredient ingredient : result) {
            System.out.println("id: " + ingredient.getId() + "\nnome: " + ingredient.getName());
        }

        assertEquals(ingredients, result);
    }

    @Test
    public void mustReturnIngredientById() {

        Long id = 1L;
        Ingredient ingredient1 = new Ingredient(id, "banana");

        when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient1));

        try {
            Ingredient result = ingredientService.listIngredientById(id);
            assertEquals(ingredient1, result);
        } catch (IngredientNotFound e) {
            fail("Ingrediente não encontrado pelo id: " + id);
        }
    }

    @Test
    public void testWhenIngredientDoesNotExist() {
        Long id = 2L;
        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IngredientNotFound.class, () -> ingredientService.listIngredientById(id));
    }

    @Test
    public void testUpdateIngredient() {
        Long id = 1L;
        Ingredient existingIngredient = new Ingredient(id, "banana");


        Ingredient updateIngredient = new Ingredient(id, "abacaxi");

        when(ingredientRepository.findById(id)).thenReturn(Optional.of(existingIngredient));

        when(ingredientRepository.save(existingIngredient)).thenReturn(updateIngredient);

        try {
            Ingredient result = ingredientService.updateIngredient(id, updateIngredient);
            assertEquals(updateIngredient, result);
            assertEquals("abacaxi", result.getName());
        } catch (IngredientNotFound e) {
            fail("Ingrediente não encontrado pelo id: " + id);
        }

        verify(ingredientRepository, times(1)).findById(id);

        verify(ingredientRepository, times(1)).save(existingIngredient);
    }

    @Test
    public void testUpdateIngredientWhenIngredientDoesNotExist() {
        Long id = 1L;
        Ingredient updateIngredient = new Ingredient(id, "farinha de trigo");

        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IngredientNotFound.class, () -> {
            ingredientService.updateIngredient(id, updateIngredient);
        });

        verify(ingredientRepository, times(1)).findById(id);

        verify(ingredientRepository, never()).save(any(Ingredient.class));
    }

    @Test
    public void deleteIngredientById() {
        Long id = 1L;

        doNothing().when(ingredientRepository).deleteById(id);

        ingredientService.deleteIngredient(id);
        verify(ingredientRepository, times(1)).deleteById(id);

    }
}
