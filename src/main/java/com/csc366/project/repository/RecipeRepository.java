package com.csc366.project.repository;

import com.csc366.project.entity.Recipe;
import com.csc366.project.entity.key.RecipeKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, RecipeKey> {
    Recipe findByKey(RecipeKey key);
}
