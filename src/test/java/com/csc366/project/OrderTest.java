/*package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.csc366.project.entity.Order;
import com.csc366.project.entity.Customer;
import com.csc366.project.entity.Location;
import com.csc366.project.entity.Address;
import com.csc366.project.entity.Owner;
import com.csc366.project.repository.CustomerRepository;
import com.csc366.project.repository.LocationRepository;
import com.csc366.project.repository.OrderRepository;
import com.csc366.project.repository.OwnerRepository;
import com.csc366.project.repository.AddressRepository;

import java.util.Date;
import java.util.LocalDate;

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
class OrderTest {
    private final static Logger log = LoggerFactory.getLogger(OwnerTest.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OwnerRepository ownerRepository;


    private final Customer customer = new Customer("Iron", "Man");
    private final Address address = new Address("testStreet", "testCity", "testState", "testZip");
    private final Owner owner = new Owner("testFirst testLast");
    private final Location location = new Location(LocalDate.parse("2022-01-01"), address, owner);
    private final entity.Order newOrder = new Order(location, 2.2, 4.5, Date.parse("2022-01-01"));

    @BeforeEach
    private void setup() {
	    orderRepository.saveAndFlush(order);
    }

    @Test
    @Order(1)
    public void testSaveOrder() {
        Order order1 = orderRepository.findByCustomerAndDate(customer, Date.parse("2022-01-01"));

        log.info(order1.toString());
        
        assertNotNull(customer1);
        assertEquals(customer1.getCustomerId(), customer.getCustomerId());
        assertEquals(customer1.getFirstName(), customer.getFirstName());
        assertEquals(customer1.getLastName(), customer.getLastName());
    }

    @Test
    @Order(2)
    public void testDeleteOrder() {
        orderRepository.delete(order);
        orderRepository.flush();
    }
    

    @Test
    @Order(3)
    public void testFindAll() {
        assertNotNull(customerRepository.findAll());
    }

    @Test
    @Order(5)
    public void testJpqlFinder() {
        Customer cust = customerRepository.findByFirstName("Iron");
        assertEquals(customer, cust);
    }
}*/
