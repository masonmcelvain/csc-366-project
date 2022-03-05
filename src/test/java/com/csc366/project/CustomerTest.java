package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.csc366.project.entity.Customer;

import com.csc366.project.repository.CustomerRepository;

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
    private CustomerRepository customerRepository;

    private Customer customer = new Customer("Iron", "Man");
    customer.setCustomerId((long)1);

    @BeforeEach
    private void setup() {
	    customerRepository.saveAndFlush(owner);
    }

    @Test
    @Order(1)
    public void testSaveOwner() {
        Customer customer1 = customerRepository.getById((long)1);

        log.info(customer1.toString());
        
        assertNotNull(customer1);
        assertEquals(customer1.getCustomerId(), customer.getCustomerId());
        assertEquals(customer1.getFirstName(), customer.getFirstName());
        assertEquals(customer1.getLastName(), customer.getLastName());
    }

    @Test
    @Order(2)
    public void testDeleteOwner() {
        customerRepository.delete(owner);
        customerRepository.flush();
    }

    @Test
    @Order(3)
    public void testFindAll() {
        assertNotNull(customerRepository.findAll());
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
        Customer cust = customerRepository.getById((long)1);
        assertEquals(customer, cust);
    }
}
