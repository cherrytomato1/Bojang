package com.ssafy.api.service.item;

import com.ssafy.api.request.item.ItemPutRequest;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.ItemType;
import com.ssafy.db.entity.Store;
import com.ssafy.db.repository.ItemRepository;
import com.ssafy.db.repository.ItemTypeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	final ItemRepository itemRepository;

	final ItemTypeRepository itemTypeRepository;

	@Override
	public List<Item> getItemListByStoreId(String storeId) {
		return itemRepository.findByStoreId(storeId);
	}

	@Transactional
	@Override
	public void putItemInStore(ItemPutRequest itemPutRequest, Store targetStore) {
		ItemType itemType = getItemTypeByItemTypeId(itemPutRequest.getItemType());
		Boolean onSale = itemPutRequest.getOnSale();
		Item item = Item.builder().name(itemPutRequest.getName())
			            .content(itemPutRequest.getContent())
			            .itemType(itemType).onSale(true).price(itemPutRequest.getPrice())
			            .store(targetStore).onSale(onSale == null ? false : onSale).build();
		itemRepository.save(item);
	}

	@Override
	public ItemType getItemTypeByItemTypeId(Long itemTypeId) {
		return itemTypeRepository.findById(itemTypeId).orElseThrow(
			() -> new ResourceNotFoundException("ItemType", "ItemTypeId", itemTypeId));
	}

	@Transactional
	@Override
	public void deleteItemByItemId(String itemId) {
		try {
			itemRepository.deleteById(itemId);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("item", "itemId", itemId);
		}
	}

}
