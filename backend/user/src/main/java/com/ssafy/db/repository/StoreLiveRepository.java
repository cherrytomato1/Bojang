package com.ssafy.db.repository;

import com.ssafy.db.entity.Store;
import com.ssafy.db.entity.StoreLive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StoreLiveRepository extends JpaRepository<StoreLive, Long> {
}
