package com.csc366.project.repository;

import com.csc366.project.entity.Contract;
import com.csc366.project.entity.Supplier;
import com.csc366.project.entity.Contract.ContractId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// This class will be automatically implemented by Spring and made available as a "Bean" named personRepository
@Repository
public interface ContractRepository extends JpaRepository<Contract, ContractId>{
    // query inferred from method name, code generated by Spring Framework
    Contract findBySupplier(Supplier supplier);

    // JPQL query with join
    @Query("select c from Contract c where c.product.sku = :sku")
    Contract findByProductSkuJpql(@Param("sku") String sku);
}

