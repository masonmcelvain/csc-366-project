package com.csc366.project.repository;

import com.csc366.project.entity.LineItem;
import com.csc366.project.entity.key.LineItemKey;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, LineItemKey> {

    LineItem findByKey(LineItemKey key);
}
