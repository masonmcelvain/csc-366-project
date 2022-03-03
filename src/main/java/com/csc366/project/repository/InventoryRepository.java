package com.csc366.project.repository;

import com.csc366.project.entity.Inventory;
import com.csc366.project.entity.Location;
import com.csc366.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByLocation(Location Location);
    Inventory findByProduct(Product product);
}
