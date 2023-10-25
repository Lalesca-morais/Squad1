package ServiceTest;

import br.com.zup.squad1.model.RecipeModel;
import br.com.zup.squad1.repository.RecipeRepository;
import br.com.zup.squad1.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeServiceTest {
    @InjectMocks
    private RecipeService recipeService;
    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterRecipe() {
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setName("Test Recipe");
        recipeModel.setPreparation("Test Preparation");
        recipeModel.setDifficulty("Medium");

        Mockito.when(recipeRepository.save(recipeModel)).thenReturn(recipeModel);
        RecipeModel result = recipeService.registerRecipe(recipeModel);

        assertNotNull(result);
        assertEquals("Test Recipe", result.getName());
        assertEquals("Test Preparation", result.getPreparation());
        assertEquals("Medium", result.getDifficulty());
    }

    @Test
    public void testConsultRecipe() {
        Long recipeId = 1L;
        RecipeModel recipeModel = new RecipeModel();
        recipeModel.setId(recipeId);
        recipeModel.setName("Test Recipe");
        recipeModel.setPreparation("Test Preparation");
        recipeModel.setDifficulty("Easy");

        Mockito.when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipeModel));
        RecipeModel result = recipeService.consultRecipe(recipeId);

        assertNotNull(result);
        assertEquals("Test Recipe", result.getName());
        assertEquals("Test Preparation", result.getPreparation());
        assertEquals("Easy", result.getDifficulty());
    }

    @Test
    public void testConsultRecipeNotFound() {
        Long recipeId = 1L;

        Mockito.when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());
        RecipeModel result = recipeService.consultRecipe(recipeId);

        assertEquals(null, result);
    }

    @Test
    public void testDeleteRecipe() {
        Long recipeId = 1L;
        Mockito.doNothing().when(recipeRepository).deleteById(recipeId);
        assertDoesNotThrow(() -> recipeService.deleteRecipe(recipeId));
    }

    @Test
    public void testChangeRecipe() {
        Long recipeId = 1L;
        RecipeModel newRecipeModel = new RecipeModel();
        newRecipeModel.setName("Changed Recipe");
        newRecipeModel.setPreparation("Changed Preparation");
        newRecipeModel.setDifficulty("Easy");

        RecipeModel existingRecipeModel = new RecipeModel();
        existingRecipeModel.setId(recipeId);
        existingRecipeModel.setName("Test Recipe");
        existingRecipeModel.setPreparation("Test Preparation");
        existingRecipeModel.setDifficulty("Easy");

        Mockito.when(recipeRepository.existsById(recipeId)).thenReturn(true);
        Mockito.when(recipeRepository.save(newRecipeModel)).thenReturn(newRecipeModel);

        RecipeModel result = recipeService.changeRecipe(recipeId, newRecipeModel);

        assertNotNull(result);
        assertEquals("Changed Recipe", result.getName());
        assertEquals("Changed Preparation", result.getPreparation());
        assertEquals("Easy", result.getDifficulty());
    }

    @Test
    public void testChangeRecipeNotFound() {
        Long recipeId = 1L;
        RecipeModel newRecipeModel = new RecipeModel();

        Mockito.when(recipeRepository.existsById(recipeId)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> recipeService.changeRecipe(recipeId, newRecipeModel));
        assertEquals("Receita não encontrada", exception.getMessage());
    }
}
