package com.ssafy.db.repository;

import com.ssafy.db.entity.Market;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MarketRepositoryTest {

    @Autowired
    MarketRepository marketRepository;

    @Test
    public void createMarket() {
        Market market = new Market();
        market.setId(1L);
        market.setName("싸피시장");
        marketRepository.save(market);
    }

    @Test
    public void getMarket() {
        List<Market> marketList = marketRepository.findAll();
        for (Market market : marketList) {
            System.out.println(market.getId());
            System.out.println(market.getName());
        }
    }
}