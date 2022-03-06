/*package com.csc366.project.repository;

import com.csc366.project.entity.Order;
import com.csc366.project.entity.Location;
import com.csc366.project.entity.Customer;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.customer = :customer && o.date = :date")
    Order findByCustomerAndDate(@Param("customer") Customer customer, @Param("date") Date date);
}*/
