package com.csc366.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import com.csc366.project.entity.Address;
import com.csc366.project.entity.Contract;
import com.csc366.project.entity.Location;
import com.csc366.project.entity.Packaged;
import com.csc366.project.entity.Prepared;
import com.csc366.project.entity.Product;
import com.csc366.project.entity.Owner;
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
class ContractTest {
    private final static Logger log = LoggerFactory.getLogger(ContractTest.class);
    
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired 
    LocationRepository locationRepository;

    @Autowired 
    ProductRepository productRepository;

    @Autowired 
    OwnerRepository ownerRepository;

    private final Address address = new Address("testStreet", "testCity", "testState", "testZip");
    private final Product prepared = new Prepared("sku1", "prepName", (long)10, (long)100);
    private final Product packaged = new Packaged("sku2", "packName", (long)10, Size.M);
    private final Owner owner = new Owner("testFirst testLast");
    private final Location location = new Location(LocalDate.parse("2022-01-01"), address, owner);
    private final Supplier supplier = new Supplier("testName", "testEmail", "888-888-8888", address);
    private final Contract contract =  new Contract("New", 10, 1099, LocalDate.parse("2022-01-10"), prepared, supplier, location);

    
    @BeforeEach
    private void setup() {
        addressRepository.saveAndFlush(address);
        supplierRepository.saveAndFlush(supplier);
        productRepository.saveAndFlush(prepared);
        ownerRepository.saveAndFlush(owner);
        locationRepository.saveAndFlush(location);
	    contractRepository.saveAndFlush(contract);
    }

    @Test
    @Order(1)
    public void testSaveContract() {
        Contract contract2 = contractRepository.findBySupplier(supplier);

        log.info(contract2.toString());
        
        assertNotNull(contract2);
        assertEquals(contract2.getType(), contract.getType());
        assertEquals(contract2.getQuantity(), contract.getQuantity());
        assertEquals(contract2.getRate(), contract.getRate());
        assertEquals(contract2.getDate(), contract.getDate());
    }

    @Test
    @Order(2)
    public void testGetContract() {
        Contract contract2 = contractRepository.findBySupplier(supplier);
        assertNotNull(contract2);
        assertEquals(contract2.getType(), contract.getType());
        assertEquals(contract2.getQuantity(), contract.getQuantity());
        assertEquals(contract2.getRate(), contract.getRate());
        assertEquals(contract2.getDate(), contract.getDate());
    }

    @Test
    @Order(3)
    public void testDeleteContract() {
        contractRepository.delete(contract);
        contractRepository.flush();
        assertEquals(contractRepository.findAll().size(), 0);
    }
    
    @Test
    @Order(4)
    public void testFindAllContracts() {
	    assertNotNull(contractRepository.findAll());
    }

    @Test
    @Order(5)
    public void testDeleteByContractId() {
        Contract s = contractRepository.findBySupplier(supplier);
        contractRepository.deleteById(s.getId());
        contractRepository.flush();
        assertEquals(contractRepository.findAll().size(), 0);
    }

    @Test
    @Order(7)
    public void testContractByProductJpql() {
        Contract contract2 = contractRepository.findByProductSkuJpql("sku1");
        assertNotNull(contract2);
        assertEquals(contract2.getType(), contract.getType());
        assertEquals(contract2.getQuantity(), contract.getQuantity());
        assertEquals(contract2.getRate(), contract.getRate());
        assertEquals(contract2.getDate(), contract.getDate());
    }
}
