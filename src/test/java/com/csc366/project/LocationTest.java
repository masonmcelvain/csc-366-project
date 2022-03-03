package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import com.csc366.project.entity.Address;
import com.csc366.project.entity.Contract;
import com.csc366.project.entity.Location;
import com.csc366.project.entity.Owner;
import com.csc366.project.entity.Packaged;
import com.csc366.project.entity.Prepared;
import com.csc366.project.entity.Product;
import com.csc366.project.entity.Size;
import com.csc366.project.entity.Supplier;
import com.csc366.project.repository.AddressRepository;
import com.csc366.project.repository.ContractRepository;
import com.csc366.project.repository.LocationRepository;
import com.csc366.project.repository.OwnerRepository;
import com.csc366.project.repository.ProductRepository;
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
class LocationTest {
    private final static Logger log = LoggerFactory.getLogger(LocationTest.class);

    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private LocationRepository locationRepository;

    @Autowired 
    private OwnerRepository ownerRepository;

    private final Address address = new Address("testStreet", "testCity", "testState", "testZip");
    private final Owner owner = new Owner("testFirst testLast");
    private final Location location = new Location(LocalDate.parse("2022-01-01"), address, owner);

    
    @BeforeEach
    private void setup() {
        addressRepository.saveAndFlush(address);
        ownerRepository.saveAndFlush(owner);
        locationRepository.saveAndFlush(location);
    }

    @Test
    @Order(1)
    public void testSaveLocation() {
        Location location2 = locationRepository.findByOwner(owner);
        log.info(location2.toString());
        
        assertNotNull(location2);
        assertEquals(location2.getOpenDate(), location.getOpenDate());
        assertEquals(location2.getAddress(), location.getAddress());
        assertEquals(location2.getOwner(), location.getOwner());
    }

    @Test
    @Order(2)
    public void testGetLocation() {
        Location location2 = locationRepository.findByOwner(owner);
        log.info(location2.toString());
        
        assertNotNull(location2);
        assertEquals(location2.getOpenDate(), location.getOpenDate());
        assertEquals(location2.getAddress(), location.getAddress());
        assertEquals(location2.getOwner(), location.getOwner());
    }

    @Test
    @Order(3)
    public void testDeleteLocation() {
        locationRepository.delete(location);
        locationRepository.flush();
        assertEquals(locationRepository.findAll().size(), 0);
    }

    @Test
    @Order(4)
    public void testFindAllLocation() {
	    assertNotNull(locationRepository.findAll());
    }

    @Test
    @Order(5)
    public void testDeleteByLocationId() {
        Location loc = locationRepository.findByOwner(owner);

        locationRepository.deleteById(loc.getId());
        locationRepository.flush();
        assertEquals(locationRepository.findAll().size(), 0);
    }

    @Test
    @Order(7)
    public void testLocationByAddressJpql() {
        Location loc = locationRepository.findByAddress(address);

        assertNotNull(loc);
        assertEquals(loc.getOpenDate(), location.getOpenDate());
        assertEquals(loc.getAddress(), location.getAddress());
        assertEquals(loc.getOwner(), location.getOwner());
    }
}


