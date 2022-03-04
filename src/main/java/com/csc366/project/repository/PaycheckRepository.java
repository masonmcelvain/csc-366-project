package com.csc366.project.repository;

import com.csc366.project.entity.Employee;
import com.csc366.project.entity.Paycheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaycheckRepository extends JpaRepository<Paycheck, Long> {
    Paycheck findByEmployee(Employee employee);
}
