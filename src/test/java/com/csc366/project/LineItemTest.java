package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.csc366.project.entity.Location;
import com.csc366.project.entity.Order;
import com.csc366.project.entity.Customer;
import com.csc366.project.entity.Address;
import com.csc366.project.entity.Owner;
import com.csc366.project.entity.LineItem;
import com.csc366.project.entity.Product;
import com.csc366.project.repository.LineItemRepository;
import com.csc366.project.repository.ProductRepository;
import com.csc366.project.repository.OwnerRepository;
import com.csc366.project.repository.AddressRepository;
import com.csc366.project.repository.CustomerRepository;
import com.csc366.project.repository.OrderRepository;
import com.csc366.project.repository.LocationRepository;
import com.csc366.project.entity.key.LineItemKey;

import java.util.Date;
import java.time.LocalDate;

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
    private OrderRepository orderRepository;
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
    private ProductRepository productRepository;

    private final Product product = new Product("testSku", "testProductName", (long)100);
    private final Customer customer = new Customer("Iron", "Man");
    private final Address address = new Address("testStreet", "testCity", "testState", "testZip");
    private final Owner owner = new Owner("testFirst testLast");
    private final Location location = new Location(LocalDate.parse("2022-01-01"), address, owner);
    private Date date = new Date(2022, 1, 1);
    private Order newOrder = new Order(location, 2.2, 4.5, date);
    private final LineItemKey key = new LineItemKey(product.getSku(), (long)1);
    private LineItem lineItem = new LineItem(key, product, newOrder, 3, 4.5, .8);

    @BeforeEach
    private void setup() {
        newOrder.setOrderId((long)1);
        customerRepository.saveAndFlush(customer);
        Customer c = customerRepository.findByFirstName("Iron");
        newOrder.setCustomer(customer);
        productRepository.saveAndFlush(product);
        addressRepository.saveAndFlush(address);
        ownerRepository.saveAndFlush(owner);
        locationRepository.saveAndFlush(location);
	    orderRepository.saveAndFlush(newOrder);
        lineItemRepository.saveAndFlush(lineItem);
    }
    
    @Test
    @org.junit.jupiter.api.Order(1)
    public void testSaveLineItem() {
        LineItem l1 = lineItemRepository.findByKey(key);

        log.info(l1.toString());

        assertNotNull(l1);
        assertEquals(lineItem, l1);
    }
    
    @Test
    @org.junit.jupiter.api.Order(2)
    public void testDeleteLineItem() {
        lineItemRepository.delete(lineItem);
        lineItemRepository.flush();
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void testFindAllLineItems() {
        assertNotNull(lineItemRepository.findAll());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void testJpqlFinderLineItem() {
        LineItem l1 = lineItemRepository.findByKey(key);
        assertEquals(lineItem, l1);
    }
}
