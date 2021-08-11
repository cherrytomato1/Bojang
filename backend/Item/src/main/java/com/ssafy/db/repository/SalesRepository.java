package com.ssafy.db.repository;

import com.ssafy.db.entity.Sales;
import com.ssafy.db.mapping.sales.SalesMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface SalesRepository extends JpaRepository<Sales, String> {

    Optional<Sales> findByStore_IdAndRegisterTime(String storeId, LocalDate now);

    List<SalesMapping> findByStore_Id(String storeId);

    List<SalesMapping> findByStore_IdAndRegisterTimeBetween(String storeId, LocalDate start, LocalDate end);


}
