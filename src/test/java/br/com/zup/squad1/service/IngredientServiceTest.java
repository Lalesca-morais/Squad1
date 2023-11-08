package br.com.zup.squad1.service;

import br.com.zup.squad1.exceptions.IngredientNotFound;
import br.com.zup.squad1.model.Ingredient;
import br.com.zup.squad1.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
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
        Ingredient ingredient = new Ingredient(1L, "cenoura");

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

        Mockito.when(ingredientRepository.findById(id)).thenReturn(Optional.of(existingIngredient));

        Mockito.when(ingredientRepository.save(Mockito.any(Ingredient.class))).thenAnswer(invocation -> {
            Ingredient updatedIngredient = invocation.getArgument(0);
            return updatedIngredient;
        });

        try {
            Ingredient result = ingredientService.updateIngredient(id, updateIngredient);
            assertEquals(updateIngredient.getName(), result.getName());
            assertEquals(updateIngredient.getValidity(), result.getValidity());
            assertEquals(updateIngredient.getAmount(), result.getAmount());
        } catch (IngredientNotFound e) {
            fail("Ingrediente não encontrado pelo id: " + id);
        }

        verify(ingredientRepository, times(1)).findById(id);
        verify(ingredientRepository, times(1)).save(Mockito.any(Ingredient.class));
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
    public void deleteIngredientById() throws IngredientNotFound {
        Long id = 1L;

        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        Mockito.when(ingredientRepository.findById(id)).thenReturn(Optional.of(ingredient));

        ingredientService.deleteIngredient(id);

        Mockito.verify(ingredientRepository, Mockito.times(1)).deleteById(id);
    }
}

