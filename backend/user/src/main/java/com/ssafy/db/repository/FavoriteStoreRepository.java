package com.ssafy.db.repository;

import com.ssafy.db.entity.FavoriteStore;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FavoriteStoreRepository extends JpaRepository<FavoriteStore,String> {

    @Override
    Optional<FavoriteStore> findById(String s);

    List<FavoriteStore> findByUser(User user);

    List<FavoriteStore> findByStore_NameContainingAndUser(String name, User user);

}
