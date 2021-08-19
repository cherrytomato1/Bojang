package com.ssafy.api.service.basket;

import com.ssafy.api.request.basket.BasketIdListDeleteRequest;
import com.ssafy.api.request.basket.BasketPatchRequest;
import com.ssafy.common.exception.handler.DuplicateBasketItemException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.dto.BasketResponseDto;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Basket;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.Store;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.BasketRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

	final BasketRepository basketRepository;

	final RestUtil restUtil;

	@Transactional
	@Override
	public void putItemInBasket(User user, Item item, Long amount) {
		if (basketRepository.findByItem_IdAndUser_Id(item.getId(), user.getId()).isPresent()) {
			throw new DuplicateBasketItemException(item.getId(), item.getName(), user.getId());
		}

		Basket basket = new Basket();
		basket.setItem(item);
		basket.setUser(user);
		basket.setAmount(amount);

		basketRepository.save(basket);
	}

	@Override
	public Basket getBasketByBasketId(String basketId) {
		return basketRepository.findById(basketId)
			       .orElseThrow(() -> new ResourceNotFoundException("Basket", "id", basketId));
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

	@Transactional
	@Override
	public void deleteBasketByBasketIdList(BasketIdListDeleteRequest basketIdListDeleteRequest) {
		basketRepository.deleteAllByIdInQuery(basketIdListDeleteRequest.getBasketIdList());
	}

	@Transactional
	@Override
	public Basket updateBasket(BasketPatchRequest basketPatchRequest) {
		Basket basket = getBasketByBasketId(basketPatchRequest.getBasketId());
		basket.setAmount(basketPatchRequest.getAmount());
		return basketRepository.save(basket);
	}

	@Override
	public List<Basket> getBasketListByUserId(String userId) {
		return basketRepository.findAllByUserId(userId);
	}

}
