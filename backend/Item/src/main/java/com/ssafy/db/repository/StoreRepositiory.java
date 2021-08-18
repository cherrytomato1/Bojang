package com.ssafy.db.repository;

import com.ssafy.db.entity.Store;
import com.ssafy.db.entity.User;
import com.ssafy.db.mapping.store.StoreMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepositiory extends JpaRepository<Store, String> {

    Optional<Store> findById(String s);

    Optional<Store> findByUser(User user);

    Optional<Store> findByUser_Id(@Param("userId") String id);

    // 단골 가게 검색용
    List<StoreMapping> findByNameContaining(String name);


    List<StoreMapping> findByNameContainingAndMarket_Id(String name, Long id);

    List<StoreMapping> findByMarket_IdAndStoreType_Id(Long marketId, Long storeTypeId);

    List<StoreMapping> findByMarket_Id(Long markerId);
}
