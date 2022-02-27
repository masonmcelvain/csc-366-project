package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.csc366.project.entity.Prepared;
import com.csc366.project.entity.Product;
import com.csc366.project.repository.ProductRepository;

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
 * Add, list, and remove Prepared instances
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
public class PreparedTest {

    private final static Logger log = LoggerFactory.getLogger(PreparedTest.class);

    @Autowired
    private ProductRepository productRepository;

    private final Prepared prepared = new Prepared("testSku", "testName", (long)100, (long)2000);

    @BeforeEach
    private void setup() {
        productRepository.saveAndFlush(prepared);
    }

    @Test
    public void testSavePrepared() {
        Product actual = productRepository.findBySku("testSku");

        log.info(actual.toString());

        assertNotNull(actual);
        assertEquals(prepared.getSku(), actual.getSku());
    }

    @Test
    public void testDeletePerson() {
        productRepository.delete(prepared);
        productRepository.flush();
    }

    @Test
    public void testFindAllPersons() {
        assertNotNull(productRepository.findAll());
    }

    @Test
    public void testDeletByPersonId() {
        Product actual = productRepository.findBySku("testSku");
        productRepository.deleteById(actual.getSku());
        productRepository.flush();
    }

    @Test
    public void testJpqlFinder() {
        Product actual = productRepository.findBySku("testSku");
        assertEquals(actual.getSku(), prepared.getSku());
    }
}
