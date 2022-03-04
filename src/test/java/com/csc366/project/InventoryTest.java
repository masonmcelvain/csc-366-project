package com.csc366.project;

import com.csc366.project.entity.*;
import com.csc366.project.repository.InventoryRepository;
import com.csc366.project.repository.LocationRepository;
import com.csc366.project.repository.ProductRepository;
import com.csc366.project.repository.OwnerRepository;
import com.csc366.project.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
class InventoryTest {

    private final static Logger log = LoggerFactory.getLogger(InventoryTest.class);

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private AddressRepository addressRepository;

    private final Product product = new Product("1b", "hotdog", 500L);
    private final Address address = new Address("testStreet", "testCity", "testState", "testZip");
    private final Owner owner = new Owner("testFirst testLast");
    private final Location location = new Location(LocalDate.parse("2022-01-01"), address, owner);

    private final Inventory inventory = new Inventory(location, product, 4);

    @BeforeEach
    private void setup() {
        productRepository.saveAndFlush(product);
        addressRepository.saveAndFlush(address);
        ownerRepository.saveAndFlush(owner);
        locationRepository.saveAndFlush(location);
        inventoryRepository.saveAndFlush(inventory);
    }

    @Test
    public void testSave() {
        Inventory actual = inventoryRepository.findByLocation(location);

        log.info(actual.toString());

        assertNotNull(actual);
        assertEquals(inventory, actual);
    }

    @Test
    public void testDelete() {
        inventoryRepository.delete(inventory);
        inventoryRepository.flush();
    }

    @Test
    public void testFindAll() {
        assertNotNull(inventoryRepository.findAll());
    }

    @Test
    public void testDeletById() {
        Inventory actual = inventoryRepository.findByLocation(location);
        inventoryRepository.deleteById(actual.getId());
        inventoryRepository.flush();
    }

    @Test
    public void testJpqlFinder() {
        Inventory actual = inventoryRepository.findByLocation(location);
        assertEquals(inventory, actual);
    }

    @Test
    public void findByProduct() {
        Inventory actual = inventoryRepository.findByProduct(product);
        assertEquals(inventory, actual);
    }
}
