package com.ssafy.api.service.store;

import com.ssafy.api.request.store.StoreUpdatePatchRequest;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.Store;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreService {

    Store getStore(String userId);

    String getStoreId(String userId);

    List<Item> getStoreItemList(String storeId);

    List<Store> searchStore(String storeName, Long marketId);

    String storeImgUpload(MultipartFile file, String storeId);

    Store updateStore(StoreUpdatePatchRequest request);

    List<Store> getStoreTypeList(Long marketId, Long storeTypeId);

    List<Store> getMarketList(Long marketId);


}
