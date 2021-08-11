package com.ssafy.api.service.item;

import com.ssafy.api.request.item.ItemPatchRequest;
import com.ssafy.api.request.item.ItemPutRequest;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.ItemType;
import com.ssafy.db.entity.Store;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
	List<Item> getItemListByStoreId(String storeId);

	Item getItemByItemId(String itemId);

	void putItemInStore(ItemPutRequest itemPutRequest, Store targetStore);

	void patchItemInStore(ItemPatchRequest itemPatchRequest, Store targetStore);

	ItemType getItemTypeByItemTypeId(Long itemTypeId);

	void deleteItemByItemId(String itemId, String userId);

	void itemImgUpload(MultipartFile file,String itemId);


}
