package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.csc366.project.entity.Ingredient;
import com.csc366.project.repository.IngredientRepository;

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
 * Add, list, and remove Ingredient instances
 */
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
class IngredientTest {

    private final static Logger log = LoggerFactory.getLogger(IngredientTest.class);

    @Autowired
    private IngredientRepository ingredientRepository;

    private final Ingredient ingredient = new Ingredient("test");

    @BeforeEach
    private void setup() {
        ingredientRepository.saveAndFlush(ingredient);
    }

    @Test
    public void testSaveIngredient() {
        Ingredient actual = ingredientRepository.findByName("test");

        log.info(actual.toString());

        assertNotNull(actual);
        assertEquals(ingredient.getName(), actual.getName());
    }

    @Test
    public void testDeletePerson() {
        ingredientRepository.delete(ingredient);
        ingredientRepository.flush();
    }

    @Test
    public void testFindAllPersons() {
        assertNotNull(ingredientRepository.findAll());
    }

    @Test
    public void testDeletByPersonId() {
        Ingredient actual = ingredientRepository.findByName("test");
        ingredientRepository.deleteById(actual.getName());
        ingredientRepository.flush();
    }

    @Test
    public void testJpqlFinder() {
        Ingredient actual = ingredientRepository.findByName("test");
        assertEquals(actual.getName(), ingredient.getName());
    }
}
