package com.csc366.project;

import com.csc366.project.entity.Contract;
import com.csc366.project.entity.Employee;
import com.csc366.project.entity.Paycheck;
import com.csc366.project.repository.PaycheckRepository;
import com.csc366.project.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
class PaycheckTest {

    private final static Logger log = LoggerFactory.getLogger(PaycheckTest.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PaycheckRepository paycheckRepository;


    private final Employee employee = new Employee("Luke", "Underwood",
      LocalDate.parse("2022-01-01"));

    private final Paycheck paycheck = new Paycheck(employee, "01/05/1987", 3.0, 40, 10,
      5, 0, 100, 0);

    @BeforeEach
    private void setup() {
        employeeRepository.saveAndFlush(employee);
        paycheckRepository.saveAndFlush(paycheck);
    }

    @Test
    public void testSave() {
        Paycheck actual = paycheckRepository.findByEmployee(employee);

        log.info(actual.toString());

        assertNotNull(actual);
        assertEquals(paycheck, actual);
    }

    @Test
    public void testGetPaycheck() {
        Paycheck paycheck2 = paycheckRepository.findByEmployee(employee);
        assertNotNull(paycheck2);
        assertEquals(paycheck2.getDate(), paycheck.getDate());
        assertEquals(paycheck2.getEmployee(), paycheck.getEmployee());
        assertEquals(paycheck2.getNetPay(), paycheck.getNetPay());
    }

    @Test
    public void testDelete() {
        paycheckRepository.delete(paycheck);
        paycheckRepository.flush();
    }

    @Test
    public void testFindAll() {
        assertNotNull(paycheckRepository.findAll());
    }

    @Test
    public void testDeletById() {
        Paycheck actual = paycheckRepository.findByEmployee(employee);
        paycheckRepository.deleteById(actual.getId());
        paycheckRepository.flush();
    }

    @Test
    public void testJpqlFinder() {
        Paycheck actual = paycheckRepository.findByEmployee(employee);
        assertEquals(paycheck, actual);
    }
}
