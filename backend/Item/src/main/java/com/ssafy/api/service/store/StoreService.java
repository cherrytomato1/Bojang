package com.ssafy.api.service.store;

import com.ssafy.db.entity.Store;

public interface StoreService {

	Store getStoreByUserId(String userId);

	boolean validateStoreId(String storeId, Store store);
}
