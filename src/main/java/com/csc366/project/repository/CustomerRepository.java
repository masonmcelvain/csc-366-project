package com.csc366.project.repository;

import com.csc366.project.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findByFirstName(String firstname);
}
