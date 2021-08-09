package com.ssafy.db.repository;

import com.ssafy.db.entity.FavoriteStore;
import com.ssafy.db.entity.Store;
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
class FavoriteStoreRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepositiory storeRepositiory;

    @Autowired
    FavoriteStoreRepository favoriteStoreRepository;

    @Test
    public void createFavStore(){
        User user = userRepository.findById("S99b735248a08").get();
        Store store1 = storeRepositiory.findByUser(userRepository.findById("S8251aec77671").get()).get();
        //Store store = storeRepositiory.findByUser(user).get();

        FavoriteStore favoriteStore = new FavoriteStore();
        favoriteStore.setUser(user);
        favoriteStore.setStore(store1);
        favoriteStoreRepository.save(favoriteStore);
    }
    @Test
    public void getFavStore(){
        User user = userRepository.findById("S46fd7ccf483e").get();
        List<FavoriteStore> list = favoriteStoreRepository.findByUser(user);
        List<Store> storeList = null;
        System.out.println(storeList);
        for (FavoriteStore favoriteStore : list){
            Store store = favoriteStore.getStore();
            storeList.add(store);
            System.out.println(store.getName());
        }
    }


    @Test
    public void findByStoreName(){
        User user = userRepository.findById("S99b735248a08").get();
        List<FavoriteStore> list = favoriteStoreRepository.findByStore_NameContainingAndUser("가게",user);
        System.out.println(list.size());
        for (FavoriteStore favoriteStore : list){
            System.out.println(favoriteStore.getId());
            Store store = favoriteStore.getStore();
            System.out.println(store.getName());
        }
    }


    @Test
    public void delete(){
        FavoriteStore favoriteStore = favoriteStoreRepository.findById("S6efd140ce32e").get();
        favoriteStoreRepository.delete(favoriteStore);
    }

}