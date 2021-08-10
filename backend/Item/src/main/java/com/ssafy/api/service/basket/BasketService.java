package com.ssafy.api.service.basket;

import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.User;

public interface BasketService {

	void putItemInBasket(User user, Item item, Long amount);
}
