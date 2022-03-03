package com.csc366.project.repository;

import com.csc366.project.entity.Employee;
import com.csc366.project.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByFirstName(String name);
    Employee findByLastName(String name);
    Employee findByLocation(Location location);
    Employee findByStartDate(LocalDate date);
}
