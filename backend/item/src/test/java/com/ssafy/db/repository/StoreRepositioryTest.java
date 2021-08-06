package com.ssafy.db.repository;

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
class StoreRepositioryTest {

    @Autowired
    StoreRepositiory storeRepositiory;

    @Autowired
    UserRepository userRepository;

    @Test
    public void createStore(){
        Store store = new Store();
        
        store.setName("생선가게");
        store.setSection(2);
        User user = userRepository.findById("S8251aec77671").get();
        System.out.println(user.getId());
        store.setUser(user);

        storeRepositiory.save(store);

        System.out.println(store.getId());


    }

    @Test
    public void findcreateStoreByUserId(){

        User user = userRepository.findById("S99b735248a08").get();
        Store store = storeRepositiory.findByUser(user).get();

        Store store1 = storeRepositiory.findByUser_Id("S99b735248a08").get();

        System.out.println(store.getId());

        System.out.println(store1.getId());

    }

    @Test
    public void findByName(){
        List<Store> list = storeRepositiory.findByNameContaining("가게");
        for(Store store : list){
            System.out.println(store.getId());
            System.out.println(store.getName());
        }
    }



}