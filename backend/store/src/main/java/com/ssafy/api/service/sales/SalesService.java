package com.ssafy.api.service.sales;

import com.ssafy.db.entity.Sales;
import com.ssafy.db.mapping.sales.SalesMapping;

import java.time.LocalDate;
import java.util.List;

public interface SalesService {
    Sales updateSales(String userId,LocalDate now, Integer sum);

    List<SalesMapping> getSales(String userId);

    List<SalesMapping> getDateSales(String userId, LocalDate start, LocalDate end);

    Sales getDateSales(String userId, LocalDate start);

}
