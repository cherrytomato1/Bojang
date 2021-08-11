package com.ssafy.api.service.orderInfo;

import com.ssafy.db.entity.OrderInfo;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Override
    public OrderInfo getOrderInfo(String orderInfoId) {
        Optional<OrderInfo> optionalOrderInfo = orderInfoRepository.findById(orderInfoId);
        if (optionalOrderInfo.isPresent())
            return optionalOrderInfo.get();
        return null;
    }

    @Override
    public List<OrderInfo> getOrderInfoList(User user, Integer year) {

        if (year == null) {
            LocalDateTime endDatetime = LocalDateTime.now();
            LocalDateTime startDatetime = endDatetime.minusMonths(6);
            List<OrderInfo> orderInfoList = orderInfoRepository.findByRegisterTimeBetweenAndUser(startDatetime, endDatetime, user);
            return orderInfoList;
        } else {
            LocalDateTime start = LocalDateTime.of(year, 1, 1, 0, 0, 0);
            LocalDateTime end = LocalDateTime.of(year, 12, 31, 23, 59, 59);
            List<OrderInfo> orderInfoList = orderInfoRepository.findByRegisterTimeBetweenAndUser(start, end, user);
            return orderInfoList;
        }
    }

    @Override
    public List<OrderInfo> getMarketInfoList(Long marketId) {
        return orderInfoRepository.findByMarket_IdAndOrderStatus_Id(marketId, 1L);
    }
}
