package com.ssafy.db.repository;

import com.ssafy.db.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MarketRepository extends JpaRepository<Market,Long> {
}
