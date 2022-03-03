package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.csc366.project.entity.Address;
import com.csc366.project.entity.Supplier;
import com.csc366.project.repository.AddressRepository;
import com.csc366.project.repository.SupplierRepository;

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
class SupplierTest {
    private final static Logger log = LoggerFactory.getLogger(SupplierTest.class);
    
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private AddressRepository addressRepository;

    private final Address address = new Address("testStreet", "testCity", "testState", "testZip");
    private final Supplier supplier = new Supplier("testName", "testEmail", "888-888-8888", address);  
    
    @BeforeEach
    private void setup() {
        addressRepository.saveAndFlush(address);
	    supplierRepository.saveAndFlush(supplier);
    }

    @Test
    @Order(1)
    public void testSaveSupplier() {
        Supplier supplier2 = supplierRepository.findByName("testName");

        log.info(supplier2.toString());
        
        assertNotNull(supplier2);
        assertEquals(supplier2.getName(), supplier.getName());
        assertEquals(supplier2.getEmailAddress(), supplier.getEmailAddress());
    }

    @Test
    @Order(2)
    public void testGetSupplier() {
        Supplier supplier2 = supplierRepository.findByName("testName");
        assertNotNull(supplier2);
        assertEquals(supplier2.getName(), supplier.getName());
        assertEquals(supplier2.getEmailAddress(), supplier.getEmailAddress());
    }

    @Test
    @Order(3)
    public void testDeleteSupplier() {
        supplierRepository.delete(supplier);
        supplierRepository.flush();
        assertEquals(supplierRepository.findAll().size(), 0);
    }
    
    @Test
    @Order(4)
    public void testFindAllSuppliers() {
	    assertNotNull(supplierRepository.findAll());
    }

    @Test
    @Order(5)
    public void testDeleteBySupplierId() {
        Supplier s = supplierRepository.findByName("testName");
        supplierRepository.deleteById(s.getId());
        supplierRepository.flush();
        assertEquals(supplierRepository.findAll().size(), 0);
    }

    @Test
    @Order(6)
    public void testGetAddress() {
        Supplier s = supplierRepository.findByName("testName");
        assertNotNull(s.getAddress());
        assertEquals(s.getAddress().getStreet(), "testStreet");
    }

    @Test
    @Order(7)
    public void testSupplierByAddressJpql() {
        Supplier supplier2 = supplierRepository.findByStreetJpql("testStreet");
        assertEquals(supplier2.getName(), supplier.getName());
        assertEquals(supplier2.getEmailAddress(), supplier.getEmailAddress());
    }
}
