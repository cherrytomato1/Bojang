package com.ssafy.db.repository;

import com.ssafy.db.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MarketRepository extends JpaRepository<Market,Long> {

    Optional<Market> findById(Long id);

}
