package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
	"spring.main.banner-mode=off",
	"logging.level.root=ERROR",
	"logging.level.csc366=DEBUG",

        // uncomment these, and the @ActiveProfiles & @AutoConfig... annotations (below) to connect this test to MySQL
        //"spring.jpa.hibernate.ddl-auto=update",
        //"spring.datasource.url=jdbc:mysql://mysql.labthreesixsix.com/<your username>",
        //"spring.datasource.username=<your username>",
        //"spring.datasource.password=<your password>",
        
	"logging.level.org.hibernate.SQL=DEBUG",
	"logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
	"spring.jpa.properties.hibernate.format_sql=true",
	"spring.jpa.show-sql=false",   // prevent duplicate logging
	"spring.jpa.properties.hibernate.show_sql=false",	
	
	"logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
//@ActiveProfiles("junit")
//@AutoConfigureTestDatabase(replace=Replace.NONE)   // without this, @DataJpaTest always uses in-memory H2 database
@TestMethodOrder(OrderAnnotation.class)
public class Demo3 {

    private final static Logger log = LoggerFactory.getLogger(Demo3.class);

    // no Spring repository in this demo, all work done via JPA EntityManager
    // https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManager.html
    @Autowired
    private EntityManager entityManager;  
    
    @BeforeEach
    private void setup() {

        // clear values from previous tests
        entityManager.createNativeQuery("DELETE FROM possible_course_offering").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM course").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM textbook").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM instructor").executeUpdate();
        
        Textbook tb1 = new Textbook("DB Systems");
        entityManager.persist(tb1);
        Textbook tb2 = new Textbook("Programming Languages");
        entityManager.persist(tb2);

        Instructor i1 = new Instructor("Instructor One");
        entityManager.persist(i1);
        Instructor i2 = new Instructor("Instructor Two");
        entityManager.persist(i2);

        Course c1 = new Course("CSC 365", tb1, i1);
        entityManager.persist(c1);
        Course c2 = new Course("CSC 366", tb1, i1);
        entityManager.persist(c2);
        Course c3 = new Course("CSC 430", tb2, i2);
        entityManager.persist(c3);
        
	entityManager.flush();  // "Synchronize the persistence context to the underlying database"
        entityManager.clear();  // "Clear the persistence context, causing all managed entities to become detached."
    }
    
    @Test
    @Order(1)
    @Rollback(false)
    public void testFinaAllCourses() {
	List<Course> cs = entityManager.createQuery("select c from Course c", Course.class).getResultList();
	log.info(cs.toString());

        log.info(cs.get(0).getTextbooks().toString());
        log.info(cs.get(0).getInstructors().toString());
    }

    @Test
    @Order(2)
    @Rollback(false)
    public void testAddTextbook() {
        Textbook tb1 = new Textbook("A First Course in DB Systems");
        entityManager.persist(tb1);
        Textbook tb2 = new Textbook("Second Course in DB Systems");
        entityManager.persist(tb2);
        Instructor i1 = new Instructor("New Instructor A");
        entityManager.persist(i1);
        Instructor i2 = new Instructor("New Instructor B");
        entityManager.persist(i2);
        
        Long rowCount = (Long) entityManager.createQuery("select count(*) from PossibleCourseOffering").getSingleResult();
	log.info("PossibleCourseOffering COUNT (before): " + rowCount);
        
        Course c = entityManager.find(Course.class, "CSC 365");
        c.addTextbook(tb1);
        c.addInstructor(i1);
        entityManager.persist(c);
        entityManager.flush();
        entityManager.clear();

        rowCount = (Long) entityManager.createQuery("select count(*) from PossibleCourseOffering").getSingleResult();
        log.info("PossibleCourseOffering COUNT (after adding 1 testbook, 1 instructor): " + rowCount);
        
        c = entityManager.find(Course.class, "CSC 365");
        c.addInstructor(i2);
        c.addTextbook(tb2);
        entityManager.persist(c);
        entityManager.flush();
        entityManager.clear();

        rowCount = (Long) entityManager.createQuery("select count(*) from PossibleCourseOffering").getSingleResult();
        log.info("PossibleCourseOffering COUNT (after 1 additional instructor and 1 textbook): " + rowCount);
        
        c = entityManager.find(Course.class, "CSC 365");
	log.info(c.toString());
        log.info(c.getTextbooks().toString());
        log.info(c.getInstructors().toString());
    }    

}
