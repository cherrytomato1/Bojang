package com.ssafy.api.service.sales;

import com.ssafy.db.entity.Sales;
import com.ssafy.db.entity.Store;
import com.ssafy.db.mapping.sales.SalesMapping;
import com.ssafy.db.repository.SalesRepository;
import com.ssafy.db.repository.StoreRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    SalesRepository salesRepository;

    @Autowired
    StoreRepositiory storeRepositiory;

    @Override
    public Sales updateSales(String userId, LocalDate now, Integer amount) {
        Optional<Store> storeOptional = storeRepositiory.findByUser_Id(userId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            Optional<Sales> salesOptional = salesRepository.findByStore_IdAndRegisterTime(store.getId(), now);
            if (salesOptional.isPresent()) {
                Sales sales = salesOptional.get();
                if (amount > 0)
                    sales.setAmount(sales.getAmount() + amount);
                return salesRepository.save(sales);
            } else {
                Sales sales = new Sales();
                sales.setStore(store);
                sales.setAmount(amount);
                return salesRepository.save(sales);
            }
        }
        return null;
    }

    @Override
    public List<SalesMapping> getSales(String userId) {
        Optional<Store> storeOptional = storeRepositiory.findByUser_Id(userId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            List<SalesMapping> salesList = salesRepository.findByStore_Id(store.getId());
            return salesList;
        }
        return null;
    }

    @Override
    public List<SalesMapping> getDateSales(String userId, LocalDate start, LocalDate end) {
        Optional<Store> storeOptional = storeRepositiory.findByUser_Id(userId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            List<SalesMapping> salesList = salesRepository.findByStore_IdAndRegisterTimeBetween(store.getId(), start, end);
            return salesList;
        }
        return null;
    }

    @Override
    public Sales getDateSales(String userId, LocalDate start) {
        Optional<Store> storeOptional = storeRepositiory.findByUser_Id(userId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            Optional<Sales> salesOptional = salesRepository.findByStore_IdAndRegisterTime(store.getId(), start);
            if (salesOptional.isPresent()) {
                return salesOptional.get();
            }
        }
        return null;
    }
}
