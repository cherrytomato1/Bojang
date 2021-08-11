package com.ssafy.api.service.basket;

import com.ssafy.api.request.basket.BasketIdListDeleteRequest;
import com.ssafy.api.request.basket.BasketPatchRequest;
import com.ssafy.common.model.dto.BasketResponseDto;
import com.ssafy.db.entity.Basket;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.User;
import java.util.List;

public interface BasketService {

	void putItemInBasket(User user, Item item, Long amount);

	Basket getBasketByBasketId(String basketId);

	List<Basket> getBasketListByUserId(String userId);

	List<BasketResponseDto> getBasketDtoListByUserId(String userId);

	void deleteBasketByBasketIdList(BasketIdListDeleteRequest basketIdListDeleteRequest);

	Basket updateBasket(BasketPatchRequest basketPatchRequest);
}
