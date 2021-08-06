package com.ssafy.db.repository;

import com.ssafy.db.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ItemRepository extends JpaRepository<Item,String> {
}
