package com.csc366.project.repository;

import com.csc366.project.entity.Owner;

import org.springframework.data.jpa.repository.JpaRepository;
import com.csc366.project.entity.Location;

import org.springframework.stereotype.Repository;

// This class will be automatically implemented by Spring and made available as a "Bean" named personRepository
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    // Owner findByLocation(Location loc);
    Owner findByName(String name);

}

