package com.ssafy.api.service.store;

import com.ssafy.db.entity.Store;

public interface StoreService {

	boolean validateStoreByUserId(String userId, Store store);
}
