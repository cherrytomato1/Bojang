package com.ssafy.db.repository;

import com.ssafy.db.entity.StoreType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StoreTypeRepositoryTest {

    @Autowired
    StoreTypeRepository storeTypeRepository;

    @Test
    public void createStoreType() {
        StoreType storeType = new StoreType();
        storeType.setId(2L);
        storeType.setName("축산물");
        storeTypeRepository.save(storeType);
    }

}