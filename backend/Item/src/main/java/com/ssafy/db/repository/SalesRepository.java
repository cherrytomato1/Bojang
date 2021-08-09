package com.ssafy.db.repository;

import com.ssafy.db.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SalesRepository extends JpaRepository<Sales, String> {
}
