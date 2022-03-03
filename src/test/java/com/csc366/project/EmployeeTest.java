package com.csc366.project;

import com.csc366.project.entity.Address;
import com.csc366.project.entity.Employee;
import com.csc366.project.entity.Location;
import com.csc366.project.entity.Owner;
import com.csc366.project.repository.*;
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
 * Add, list, and remove Employee instances
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
class EmployeeTest {

    private final static Logger log = LoggerFactory.getLogger(EmployeeTest.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private AddressRepository addressRepository;

    private final Address address = new Address("testStreet", "testCity", "testState", "testZip");
    private final Owner owner = new Owner("testFirst testLast");
    private final Location location = new Location(LocalDate.parse("2022-01-01"), address, owner);
    private final Employee employee = new Employee("Luke", "Underwood",
      LocalDate.parse("2022-01-01"));

    @BeforeEach
    private void setup() {
        addressRepository.saveAndFlush(address);
        ownerRepository.saveAndFlush(owner);
        locationRepository.saveAndFlush(location);
        employeeRepository.saveAndFlush(employee);
        employee.setLocation(location);
    }

    @Test
    public void testSave() {
        Employee actual = employeeRepository.findByFirstName("Luke");

        log.info(actual.toString());

        assertNotNull(actual);
        assertEquals(employee, actual);
    }

    @Test
    public void testDelete() {
        employeeRepository.delete(employee);
        employeeRepository.flush();
    }

    @Test
    public void testFindAll() {
        assertNotNull(employeeRepository.findAll());
    }

    @Test
    public void testDeletById() {
        Employee actual = employeeRepository.findByFirstName("Luke");
        employeeRepository.deleteById(actual.getId());
        employeeRepository.flush();
    }

    @Test
    public void testJpqlFinder() {
        Employee actual = employeeRepository.findByFirstName("Luke");
        assertEquals(employee, actual);
    }

    @Test
    public void testFindByLocation() {
        Employee actual = employeeRepository.findByLocation(location);
        assertEquals(employee, actual);
    }

    @Test
    public void testFindByStartDate() {
        Employee actual = employeeRepository.findByStartDate(LocalDate.parse("2022-01-01"));
        assertEquals(employee, actual);
    }

    @Test
    public void testFindByLastName() {
        Employee actual = employeeRepository.findByLastName("Underwood");
        assertEquals(employee, actual);
    }
}
