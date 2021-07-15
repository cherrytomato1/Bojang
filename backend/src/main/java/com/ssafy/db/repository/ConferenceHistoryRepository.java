package com.ssafy.db.repository;

import com.ssafy.db.entity.Conference_history;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface ConferenceHistoryRepository extends JpaRepository<Conference_history,Long> {
    Collection<Conference_history> findByUserId(User user);

}
