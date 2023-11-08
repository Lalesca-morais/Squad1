package br.com.zup.squad1.repository;

import br.com.zup.squad1.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByValidityBetween(LocalDate start, LocalDate end);

    @Query("SELECT i FROM Ingredient i WHERE i.validity < :validity")
    List<Ingredient> findByExpiredIngredients(@Param("validity")LocalDate date);
}

