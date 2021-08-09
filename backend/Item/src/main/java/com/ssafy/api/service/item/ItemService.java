package com.ssafy.api.service.item;

import com.ssafy.api.request.item.ItemPutRequest;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.ItemType;
import com.ssafy.db.entity.Store;
import java.util.List;

public interface ItemService {
	List<Item> getItemListByStoreId(String storeId);

	void putItemInStore(ItemPutRequest itemPutRequest, Store targetStore);

	ItemType getItemTypeByItemTypeId(Long itemTypeId);

	void deleteItemByItemId(String itemId);
}
