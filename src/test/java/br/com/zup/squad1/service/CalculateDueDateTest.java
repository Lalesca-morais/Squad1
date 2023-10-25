package br.com.zup.squad1.service;

import br.com.zup.squad1.model.Ingredient;
import br.com.zup.squad1.model.enums.ProductType;
import br.com.zup.squad1.model.enums.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculateDueDateTest {
    @InjectMocks
    private IngredientService ingredientService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateDueDateForFruit() {
        Ingredient ingredient = new Ingredient();

        ingredient.setProductType(ProductType.FRUIT);

        ingredient.setState(State.GREEN);
        LocalDate dueDate = ingredientService.calculateDueDate(ingredient);
        assertEquals(LocalDate.now().plus(15, ChronoUnit.DAYS), dueDate);

        ingredient.setState(State.ALMOST_MATURE);
        dueDate = ingredientService.calculateDueDate(ingredient);
        assertEquals(LocalDate.now().plus(7, ChronoUnit.DAYS), dueDate);

        ingredient.setState(State.MATURE);
        dueDate = ingredientService.calculateDueDate(ingredient);
        assertEquals(LocalDate.now().plus(3, ChronoUnit.DAYS), dueDate);

    }

    @Test
    public void testCalculateDueDateForBakery() {
        Ingredient ingredient = new Ingredient();

        ingredient.setProductType(ProductType.BAKERY);

        LocalDate dueDate = ingredientService.calculateDueDate(ingredient);
        assertEquals(LocalDate.now().plus(1, ChronoUnit.DAYS), dueDate);
    }

    @Test
    public void testCalculateDueDateForNonPerishable(){
        Ingredient ingredient = new Ingredient();

        ingredient.setProductType(ProductType.NON_PERISHABLE);

        LocalDate dueDate = ingredientService.calculateDueDate(ingredient);
        assertEquals((LocalDate.now().plus(20, ChronoUnit.YEARS)), dueDate);
    }
}
