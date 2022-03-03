package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.csc366.project.entity.Address;
import com.csc366.project.repository.AddressRepository;

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
class AddressTest {
    private final static Logger log = LoggerFactory.getLogger(AddressTest.class);
    
    @Autowired
    private AddressRepository addressRepository;

    private final Address address = new Address("123 Main St.", "San Luis Obispo", "CA", "91361");  
    
    @BeforeEach
    private void setup() {
	    addressRepository.saveAndFlush(address);
    }

    @Test
    @Order(1)
    public void testSaveAddress() {
        Address address2 = addressRepository.findByStreet("123 Main St.");

        log.info(address2.toString());
        
        assertNotNull(address2);
        assertEquals(address2.getStreet(), address.getStreet());
        assertEquals(address2.getCity(), address.getCity());
    }

    @Test
    @Order(2)
    public void testGetAddress() {
        Address address2 = addressRepository.findByStreet("123 Main St.");
        assertNotNull(address2);
        assertEquals(address2.getStreet(), address.getStreet());
        assertEquals(address2.getCity(), address.getCity());
    }

    @Test
    @Order(3)
    public void testDeleteAddress() {
        addressRepository.delete(address);
        addressRepository.flush();
        assertEquals(addressRepository.findAll().size(), 0);
    }
    
    @Test
    @Order(4)
    public void testFindAllAddresss() {
	    assertNotNull(addressRepository.findAll());
    }

    @Test
    @Order(5)
    public void testDeleteByAddressId() {
        Address e = addressRepository.findByStreet("123 Main St.");
        addressRepository.deleteById(e.getId());
        addressRepository.flush();
        assertEquals(addressRepository.findAll().size(), 0);
    }

}
