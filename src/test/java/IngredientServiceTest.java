import com.example.squad1.controllers.controller.request.IngredientRequest;
import com.example.squad1.models.model.IngredientModel;
import com.example.squad1.repositories.repository.IngredientRepository;
import com.example.squad1.services.service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.Optional;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientServiceTest {
    @InjectMocks
    private IngredientService ingredientService;
    @Mock
    private IngredientRepository ingredientRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateIngredient() {
        IngredientRequest ingredientRequest = new IngredientRequest();
        ingredientRequest.setNameRequest("Test Ingredient");
        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setName(ingredientRequest.getNameRequest());

        Mockito.when(ingredientRepository.save(ingredientModel)).thenReturn(ingredientModel);
        IngredientModel result = ingredientService.createIngredient(ingredientRequest);

        assertNotNull(result);
        assertEquals("Test Ingredient", result.getName());
    }

    @Test
    public void testGetIngredient() {
        Long ingredientId = 1L;
        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setId(ingredientId);
        ingredientModel.setName("Test Ingredient");

        Mockito.when(ingredientRepository.findById(ingredientId)).thenReturn(Optional.of(ingredientModel));

        IngredientModel result = ingredientService.getIngredient(ingredientId);

        assertNotNull(result);
        assertEquals(ingredientId, result.getId());
        assertEquals("Test Ingredient", result.getName());
    }

    @Test
    public void testGetIngredientNotFound() {
        Long ingredientId = 1L;

        Mockito.when(ingredientRepository.findById(ingredientId)).thenReturn(Optional.empty());
        IngredientModel result = ingredientService.getIngredient(ingredientId);

        assertEquals(null, result);
    }

    @Test
    public void testDeleteIngredient() {
        Long ingredientId = 1L;
        Mockito.doNothing().when(ingredientRepository).deleteById(ingredientId);

        assertDoesNotThrow(() -> ingredientService.deleteIngredient(ingredientId));
    }

    @Test
    public void testUpdateIngredient() {
        Long ingredientId = 1L;
        IngredientModel updatedIngredient = new IngredientModel();
        updatedIngredient.setId(ingredientId);
        updatedIngredient.setName("Changed Ingredient");
        IngredientModel existingIngredient = new IngredientModel();
        existingIngredient.setId(ingredientId);
        existingIngredient.setName("Test Ingredient");

        Mockito.when(ingredientRepository.existsById(ingredientId)).thenReturn(true);
        Mockito.when(ingredientRepository.save(updatedIngredient)).thenReturn(updatedIngredient);

        IngredientModel result = ingredientService.updateIngredient(ingredientId, updatedIngredient);

        assertNotNull(result);
        assertEquals(ingredientId, result.getId());
        assertEquals("Changed Ingredient", result.getName());
    }

    @Test
    public void testUpdateIngredientNotFound() {
        Long ingredientId = 1L;
        IngredientModel updatedIngredient = new IngredientModel();

        Mockito.when(ingredientRepository.existsById(ingredientId)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ingredientService.updateIngredient(ingredientId, updatedIngredient));
        assertEquals("Ingrediente não encontrado", exception.getMessage());
    }
}
