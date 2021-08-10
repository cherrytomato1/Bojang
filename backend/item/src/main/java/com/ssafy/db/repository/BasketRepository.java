package com.ssafy.db.repository;

import com.ssafy.db.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket,String> {
}
