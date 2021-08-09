package com.ssafy.db.repository;

import com.ssafy.db.entity.PayType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PayTypeRepository extends JpaRepository<PayType,Long> {
}
