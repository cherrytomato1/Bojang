package com.ssafy.db.repository;

import com.ssafy.db.entity.Conference_history;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface ConferenceHistoryRepository extends JpaRepository<Conference_history,Long> {
    List<Conference_history> findByUser(User user);

}
