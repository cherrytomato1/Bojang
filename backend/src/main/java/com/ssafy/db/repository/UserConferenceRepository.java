package com.ssafy.db.repository;

import com.ssafy.db.entity.User;
import com.ssafy.db.entity.User_conference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserConferenceRepository extends JpaRepository<User_conference, Long> {
    Collection<User_conference> findByUserId(User user);
}
