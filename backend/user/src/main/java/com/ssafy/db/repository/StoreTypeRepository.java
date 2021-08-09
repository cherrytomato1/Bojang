package com.ssafy.db.repository;

import com.ssafy.db.entity.StoreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StoreTypeRepository extends JpaRepository<StoreType,Long> {
}
