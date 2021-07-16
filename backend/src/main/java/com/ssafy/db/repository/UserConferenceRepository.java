package com.ssafy.db.repository;

import com.ssafy.db.entity.User;
import com.ssafy.db.entity.User_conference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserConferenceRepository extends JpaRepository<User_conference, Long> {
    List<User_conference> findByUser(User user);
}
