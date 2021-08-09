package com.ssafy.api.service.item;

import com.ssafy.db.entity.Item;
import com.ssafy.db.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

	ItemRepository itemRepository;

	@Override
	public List<Item> getItemList(String storeId) {
		return itemRepository.findByStoreId(storeId);
	}
}
