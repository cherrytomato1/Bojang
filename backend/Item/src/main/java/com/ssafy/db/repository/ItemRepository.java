package com.ssafy.db.repository;

import com.ssafy.db.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item,String> {
}
