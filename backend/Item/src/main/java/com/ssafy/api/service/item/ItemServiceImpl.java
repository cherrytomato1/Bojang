package com.ssafy.api.service.item;

import com.ssafy.api.request.item.ItemPatchRequest;
import com.ssafy.api.request.item.ItemPutRequest;
import com.ssafy.common.exception.handler.AuthException;
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

	@Override
	public Item getItemByItemId(String itemId) {
		return itemRepository.findById(itemId)
			       .orElseThrow(() -> new ResourceNotFoundException("Item", "Id", itemId));

	}

	@Transactional
	@Override
	public void putItemInStore(ItemPutRequest itemPutRequest, Store targetStore) {
		ItemType itemType = getItemTypeByItemTypeId(itemPutRequest.getItemType());
		Boolean onSale = itemPutRequest.getOnSale();
		Item item = Item.builder().name(itemPutRequest.getName())
			            .content(itemPutRequest.getContent())
			            .itemType(itemType).onSale(true).price(itemPutRequest.getPrice())
			            .store(targetStore).onSale(onSale == null ? false : onSale)
			            .image(itemPutRequest.getImage()).build();
		itemRepository.save(item);
	}

	@Transactional
	@Override
	public void patchItemInStore(ItemPatchRequest itemPatchRequest, Store targetStore) {
		Item item = getItemByItemId(itemPatchRequest.getItemId());
		if (!item.getStore().equals(targetStore)) {
			throw new AuthException("가게에 등록되어 있는 아이템이 아닙니다.");
		}

		ItemType itemType = getItemTypeByItemTypeId(itemPatchRequest.getItemType());
		item.setItemType(itemType);
		item.setContent(itemPatchRequest.getContent());
		item.setName(itemPatchRequest.getName());
		item.setImage(itemPatchRequest.getImage());
		item.setPrice(itemPatchRequest.getPrice());
		item.setOnSale(itemPatchRequest.getOnSale());

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
