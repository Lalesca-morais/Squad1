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
    @Query("SELECT DISTINCT r FROM RecipeModel r JOIN r.ingredients ri WHERE ri.id = :ingredientId")
    List<RecipeModel> findRecipesByIngredientId(@Param("ingredientId") Long ingredientId);
}
