package com.ssafy.db.repository;

import com.ssafy.db.entity.Sales;
import com.ssafy.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SalesRepository extends JpaRepository<Sales, String> {
}
