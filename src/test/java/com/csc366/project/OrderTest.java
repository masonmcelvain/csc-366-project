package com.csc366.project;

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
import com.csc366.project.repository.LineItemRepository;

import java.util.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
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
    private final static Logger log = LoggerFactory.getLogger(OrderTest.class);

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private LineItemRepository lineItemRepository; 
    @Autowired
    private OrderRepository orderRepository;


    private final Customer customer = new Customer("Iron", "Man");
    private final Address address = new Address("testStreet", "testCity", "testState", "testZip");
    private final Owner owner = new Owner("testFirst testLast");
    private final Location location = new Location(LocalDate.parse("2022-01-01"), address, owner);
    private final Date date = new Date(2022, 1, 1);
    private Order newOrder = new Order(location, 2.2, 4.5, date);

    @BeforeEach
    private void setup() {
        newOrder.setOrderId((long)1);
        customerRepository.saveAndFlush(customer);
        newOrder.setCustomer(customer);
        addressRepository.saveAndFlush(address);
        ownerRepository.saveAndFlush(owner);
        locationRepository.saveAndFlush(location);
	    orderRepository.saveAndFlush(newOrder);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void testSaveOrder() {
        Long id = new Long(1);
        Order order1 = orderRepository.findByOrderId(id);

        log.info(order1.toString());

        assertNotNull(order1);
        assertEquals(newOrder.getLocation(), order1.getLocation());
        assertEquals(newOrder.getTax(), order1.getTax());
        assertEquals(newOrder.getTotal(), order1.getTotal());
    }
    
    @Test
    @org.junit.jupiter.api.Order(2)
    public void testDeleteOrder() {
        orderRepository.delete(newOrder);
        orderRepository.flush();
    }
    
    @Test
    @org.junit.jupiter.api.Order(3)
    public void testFindAllOrders() {
        assertNotNull(orderRepository.findAll());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void testJpqlFinderOrder() {
        Order ord = orderRepository.findByOrderId((long)1);
        assertEquals(newOrder, ord);
    }
}
