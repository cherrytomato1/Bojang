package com.ssafy.db.repository;

import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    //authId로 이미 가입된 회원 참조
    Optional<User> findByAuthId(String authId);
}
