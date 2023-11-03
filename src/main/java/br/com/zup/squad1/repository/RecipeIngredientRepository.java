package br.com.zup.squad1.repository;

import br.com.zup.squad1.model.RecipeIngredientModel;
import br.com.zup.squad1.model.RecipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredientModel, Long> {
    @Query("SELECT r FROM RecipeModel r JOIN RecipeIngredientModel ri ON r.id = ri.id_recipe WHERE ri.id_ingredient = :id")
    List<RecipeModel> findRecipesByIngredientId(@Param("id") Long id_ingredient);

    @Query("SELECT r FROM RecipeModel r JOIN RecipeIngredientModel ri ON r.id = ri.id_recipe JOIN Ingredient i ON " +
            "ri.id_ingredient = i.id WHERE LOWER(i.name) IN (:ingredientsName) GROUP BY r HAVING COUNT(DISTINCT i.name) = :ingredientCount")
    List<RecipeModel> findRecipesByIngredientNames(@Param("ingredientsName") List<String> ingredientsName, @Param("ingredientCount") int ingredientCount);

    @Query("SELECT ri FROM RecipeIngredientModel ri WHERE ri.id_recipe = :id")
    List<RecipeIngredientModel> findRecipeIngredientsByRecipeId(@Param("id") Long id_recipe);
}
