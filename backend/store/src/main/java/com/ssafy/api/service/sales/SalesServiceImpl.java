package com.ssafy.api.service.sales;

import com.ssafy.db.entity.Sales;
import com.ssafy.db.entity.Store;
import com.ssafy.db.mapping.sales.SalesMapping;
import com.ssafy.db.repository.SalesRepository;
import com.ssafy.db.repository.StoreRepositiory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

	final SalesRepository salesRepository;

	final StoreRepositiory storeRepositiory;

	@Transactional
	@Override
	public Sales updateSales(Store targetStore, LocalDate now, Integer sum) {
		if (sum <= 0) {
			throw new IllegalArgumentException("Sum must be at least 0");
		}

		Sales sales = salesRepository.findByStore_IdAndRegisterTime(targetStore.getId(), now)
			              .orElseGet(() -> Sales.builder().sum(0).store(targetStore).build());

		sales.setSum(sales.getSum() + sum);

		return salesRepository.save(sales);
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
			List<SalesMapping> salesList = salesRepository
				                               .findByStore_IdAndRegisterTimeBetween(store.getId(),
					                               start, end);
			return salesList;
		}
		return null;
	}

	@Override
	public Sales getDateSales(String userId, LocalDate start) {
		Optional<Store> storeOptional = storeRepositiory.findByUser_Id(userId);
		if (storeOptional.isPresent()) {
			Store store = storeOptional.get();
			Optional<Sales> salesOptional = salesRepository
				                                .findByStore_IdAndRegisterTime(store.getId(),
					                                start);
			if (salesOptional.isPresent()) {
				return salesOptional.get();
			}
		}
		return null;
	}
}
