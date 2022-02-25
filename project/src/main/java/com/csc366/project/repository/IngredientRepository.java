package com.csc366.project.repository;

import com.csc366.project.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    Ingredient findByName(String name);
}
