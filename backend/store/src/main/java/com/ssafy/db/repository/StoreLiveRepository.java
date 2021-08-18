package com.ssafy.db.repository;

import com.ssafy.db.entity.StoreLive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StoreLiveRepository extends JpaRepository<StoreLive, Long> {
    Optional<StoreLive> findByStore_Id(String storeId);
}
