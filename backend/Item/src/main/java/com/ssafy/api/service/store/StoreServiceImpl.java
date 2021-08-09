package com.ssafy.api.service.store;

import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.db.entity.Store;
import com.ssafy.db.repository.StoreRepositiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

	StoreRepositiory storeRepositiory;

	@Override
	public Store getStoreByUserId(String userId) {

		return storeRepositiory.findByUser_Id(userId)
			       .orElseThrow(() -> new ResourceNotFoundException("Store", "id", userId));
	}

	@Override
	public boolean validateStoreId(String storeId, Store store) {
		if (storeId.equals(store.getId())) {
			throw new AuthException("허가되지 않은 접근입니다.");
		}
		return true;
	}
}
