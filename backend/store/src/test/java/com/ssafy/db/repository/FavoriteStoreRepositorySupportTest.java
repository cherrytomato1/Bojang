package com.ssafy.db.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.ssafy.db.entity.FavoriteStore;
import com.ssafy.db.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FavoriteStoreRepositorySupportTest {
    @Autowired
    FavoriteStoreRepositorySupport favoriteStoreRepositorySupport;
    @Autowired
    UserRepository userRepository;

    @Test
    public void findFavStore(){
        User user = userRepository.findById("S99b735248a08").get();
        List<FavoriteStore> list = favoriteStoreRepositorySupport.searchByStoreName(user,"과일");
        System.out.println(list.size());
        for (FavoriteStore favoriteStore : list){
            System.out.println(favoriteStore.getId());
        }
    }

    @Test
    public void findByUser(){
        User user = userRepository.findById("S99b735248a08").get();
        List<FavoriteStore> list = favoriteStoreRepositorySupport.searchByUser(user);
        for (FavoriteStore favoriteStore : list){
            System.out.println(favoriteStore.getId());
        }
    }
}