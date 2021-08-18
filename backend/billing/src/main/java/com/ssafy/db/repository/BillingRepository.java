package com.ssafy.db.repository;

import com.ssafy.db.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing,String> {
}
