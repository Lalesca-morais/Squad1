package com.example.squad1.repositories.repository;


import com.example.squad1.models.model.IngredientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientModel, Long> {
}

