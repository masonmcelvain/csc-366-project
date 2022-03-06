package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.csc366.project.entity.Ingredient;
import com.csc366.project.entity.Product;
import com.csc366.project.entity.Recipe;
import com.csc366.project.entity.key.RecipeKey;
import com.csc366.project.repository.IngredientRepository;
import com.csc366.project.repository.ProductRepository;
import com.csc366.project.repository.RecipeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

/**
 * Add, list, and remove Recipe instances
 */
 /*
@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {
    "spring.main.banner-mode=off",
    "spring.jpa.hibernate.ddl-auto=update",
    "logging.level.root=ERROR",
    "logging.level.csc366=DEBUG",
    "logging.level.org.hibernate.SQL=DEBUG",
    "logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
    "spring.jpa.properties.hibernate.format_sql=true",
    "spring.jpa.show-sql=false",   // prevent duplicate logging
    "spring.jpa.properties.hibernate.show_sql=false",
    "logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
class LineItemTest {

    private final static Logger log = LoggerFactory.getLogger(LineItemTest.class);

    @Autowired
    private LineItemRepository lineItemRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private final Ingredient ingredient = new Ingredient("testIngredientName");
    private final Product product = new Product("testSku", "testProductName", (long)100);
    private final RecipeKey recipeKey = new RecipeKey(product.getSku(), ingredient.getName());
    private final Recipe recipe = new Recipe(recipeKey, product, ingredient);

    @BeforeEach
    private void setup() {
        ingredientRepository.saveAndFlush(ingredient);
        productRepository.saveAndFlush(product);
        recipeRepository.saveAndFlush(recipe);
    }

    @Test
    public void testSave() {
        Recipe actual = recipeRepository.findByKey(recipeKey);

        log.info(actual.toString());

        assertNotNull(actual);
        assertEquals(recipe, actual);
    }

    @Test
    public void testDelete() {
        recipeRepository.delete(recipe);
        recipeRepository.flush();
    }

    @Test
    public void testFindAll() {
        assertNotNull(recipeRepository.findAll());
    }

    @Test
    public void testDeletById() {
        Recipe actual = recipeRepository.findByKey(recipeKey);
        recipeRepository.deleteById(actual.getKey());
        recipeRepository.flush();
    }

    @Test
    public void testJpqlFinder() {
        Recipe actual = recipeRepository.findByKey(recipeKey);
        assertEquals(recipe, actual);
    }
}*/
