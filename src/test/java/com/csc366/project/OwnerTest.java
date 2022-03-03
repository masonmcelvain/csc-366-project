package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import com.csc366.project.entity.Owner;

import com.csc366.project.repository.OwnerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
@TestMethodOrder(OrderAnnotation.class)
class OwnerTest {
    private final static Logger log = LoggerFactory.getLogger(OwnerTest.class);

    @Autowired
    private OwnerRepository ownerRepository;

    private final Owner owner = new Owner("testFirst testLast");
    
    @BeforeEach
    private void setup() {
	    ownerRepository.saveAndFlush(owner);
    }

    @Test
    @Order(1)
    public void testSaveOwner() {
        Owner owner1 = ownerRepository.findByName("testFirst testLast");

        log.info(owner1.toString());
        
        assertNotNull(owner1);
        assertEquals(owner1.getName(), owner.getName());
    }

    @Test
    @Order(2)
    public void testDeleteOwner() {
        ownerRepository.delete(owner);
        ownerRepository.flush();
    }

    @Test
    @Order(3)
    public void testFindAll() {
        assertNotNull(ownerRepository.findAll());
    }

    // @Test
    // @Order(4)
    // public void testDeleteById() {
    //     Owner own = ownerRepository.findByName("testFirst testLast");
    //     ownerRepository.deleteById(own.getName());
    //     ownerRepository.flush();
    // }

    @Test
    @Order(5)
    public void testJpqlFinder() {
        Owner own = ownerRepository.findByName("testFirst testLast");
        assertEquals(owner, own);
    }
}
