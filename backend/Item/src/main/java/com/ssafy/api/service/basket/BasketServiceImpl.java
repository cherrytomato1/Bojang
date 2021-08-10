package com.ssafy.api.service.basket;

import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Basket;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.BasketRepository;
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
	public List<Basket> getBasketListByUserId(String userId) {
		return basketRepository.findAllByUserId(userId);
	}
}
