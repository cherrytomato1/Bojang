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

	@Override
	public boolean validateStoreByUserId(String userId, Store store) {
		if (userId.equals(store.getUser().getId())) {
			throw new AuthException("허가되지 않은 접근입니다.");
		}
		return true;
	}
}
