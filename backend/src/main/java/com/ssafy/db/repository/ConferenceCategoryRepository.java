package com.ssafy.db.repository;

import com.ssafy.db.entity.Conference_category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceCategoryRepository extends JpaRepository<Conference_category,Long> {
}
