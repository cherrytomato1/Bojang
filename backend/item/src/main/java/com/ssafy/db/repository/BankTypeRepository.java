package com.ssafy.db.repository;

import com.ssafy.db.entity.BankType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankTypeRepository extends JpaRepository<BankType,Long> {
}
