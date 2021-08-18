package com.ssafy.db.repository;

import com.ssafy.db.entity.Sales;
import com.ssafy.db.mapping.sales.SalesMapping;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalesRepositoryTest {

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    StoreRepositiory storeRepositiory;

    @Test
    public void createSales() {
        Sales sales = new Sales();
        sales.setStore(storeRepositiory.findById("Sa926f12a1a24").get());
        sales.setSum(20000);
        salesRepository.save(sales);
    }

    @Test
    public void update() {
        Sales sales = salesRepository.findById("Sb68644e79601").get();
        sales.setRegisterTime(LocalDate.now().minusDays(1));
        salesRepository.save(sales);
    }

    @Test
    public void findNowSales() {
        Sales list = salesRepository.findByStore_IdAndRegisterTime("Sa926f12a1a24", LocalDate.now()).get();
        System.out.println(list.getSum());
    }

    @Test
    public void findDateSales() {
        LocalDate start = LocalDate.now().minusWeeks(2);
        LocalDate end = LocalDate.now();
        List<SalesMapping> list = salesRepository.findByStore_IdAndRegisterTimeBetween("Sa926f12a1a24", start, end);
        System.out.println(list.size());
    }
}