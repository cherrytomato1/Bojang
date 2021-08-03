package com.ssafy.db.repository;

import com.ssafy.db.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findByEmail(String email);
    //authId로 이미 가입된 회원 참조
    Optional<AuthUser> findByAuthId(String authId);
}
