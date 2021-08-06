package com.ssafy.db.repository;

import com.ssafy.db.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserTypeRepository extends JpaRepository<UserType,Long> {
}
