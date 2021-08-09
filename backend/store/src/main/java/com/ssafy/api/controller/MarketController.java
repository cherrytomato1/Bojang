package com.ssafy.api.controller;

import com.ssafy.api.response.market.MarketGetResponse;
import com.ssafy.api.service.market.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    @Autowired
    MarketService marketService;

    @GetMapping()
    public ResponseEntity<?> getMarketList() {
        return ResponseEntity.ok(MarketGetResponse.of(200, "Success", marketService.getMarKetList()));
    }
}
