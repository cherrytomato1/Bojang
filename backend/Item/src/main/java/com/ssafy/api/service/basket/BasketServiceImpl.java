package com.ssafy.api.service.basket;

import com.ssafy.common.model.dto.BasketResponseDto;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Basket;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.Market;
import com.ssafy.db.entity.Store;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.BasketRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

	final BasketRepository basketRepository;

	final RestUtil restUtil;

	@Override
	public void putItemInBasket(User user, Item item, Long amount) {
		Basket basket = new Basket();
		basket.setItem(item);
		basket.setUser(user);
		basket.setAmount(amount);

		basketRepository.save(basket);
	}

	@Override
	public List<BasketResponseDto> getBasketDtoListByUserId(String userId) {
		List<Basket> basketList = getBasketListByUserId(userId);
		List<BasketResponseDto> basketDtoList = new ArrayList<>();

		for (Basket basket : basketList) {
			Store store = basket.getItem().getStore();

			basketDtoList.add(new BasketResponseDto(basket, store.getId(), store.getName()));
		}
		return basketDtoList;
	}

	@Override
	public List<Basket> getBasketListByUserId(String userId) {
		return basketRepository.findAllByUserId(userId);
	}
}
