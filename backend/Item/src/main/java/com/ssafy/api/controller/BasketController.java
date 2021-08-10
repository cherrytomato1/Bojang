package com.ssafy.api.controller;

import com.ssafy.api.request.basket.BasketPutRequest;
import com.ssafy.api.response.basket.BasketListGetResponse;
import com.ssafy.api.response.basket.BasketPutResponse;
import com.ssafy.api.service.basket.BasketService;
import com.ssafy.api.service.item.ItemService;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.exception.handler.RestTemplateException;
import com.ssafy.common.model.dto.BasketResponseDto;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Basket;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
public class BasketController {

	final BasketService basketService;

	final ItemService itemService;

	final RestUtil restUtil;

	@PutMapping("")
	public ResponseEntity<? extends BaseResponseBody> putItemIntBasket(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@RequestBody BasketPutRequest basketPutRequest) {
		try {
			User user = restUtil.getUserByToken(token);
			Item item = itemService.getItemByItemId(basketPutRequest.getItemId());
//
			basketService.putItemInBasket(user, item, basketPutRequest.getAmount());
		} catch (AuthException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				       .body(BasketPutResponse.of(401, ex.getMessage()));
		} catch (ResourceNotFoundException | RestTemplateException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				       .body(BasketPutResponse.of(404, ex.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(BasketPutResponse.of(200, "Success"));
	}

	@GetMapping("")
	public ResponseEntity<? extends BaseResponseBody> getBasketList(
		@ApiIgnore @RequestHeader("Authorization") String token) {
		List<BasketResponseDto> basketDtoList;
		try {
			String userId = restUtil.getUserId(token);
			basketDtoList = basketService.getBasketDtoListByUserId(userId);
		} catch (AuthException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				       .body(BasketListGetResponse.of(401, "사용자 인증 실패 !", null));
		}
		return ResponseEntity.status(HttpStatus.OK)
			              .body(BasketListGetResponse.of(200, "Success", basketDtoList));
	}
}
