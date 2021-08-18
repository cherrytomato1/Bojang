package com.ssafy.api.service.market;

import com.ssafy.db.entity.Market;
import com.ssafy.db.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    MarketRepository marketRepository;


    @Override
    public List<Market> getMarKetList() {
        return marketRepository.findAll();
    }
}
