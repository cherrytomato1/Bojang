package com.ssafy.api.service.store;

import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.Store;
import com.ssafy.db.mapping.store.StoreMapping;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreService {

    Store getStore(String userId);

    Store getStoreInfo(String storeId);

    String getStoreId(String userId);

    List<Item> getStoreItemList(String storeId);

    List<StoreMapping> searchStore(String storeName, Long marketId);

    String storeImgUpload(MultipartFile file, String userId);


    List<StoreMapping> getStoreTypeList(Long marketId, Long storeTypeId);

    List<StoreMapping> getMarketList(Long marketId);

    Store updateComment(String userId, String comment);

    Resource loadFileAsResource(String fileName);

}
