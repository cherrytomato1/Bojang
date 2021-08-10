package com.ssafy.api.controller;

import com.ssafy.api.request.basket.BasketPutRequest;
import com.ssafy.api.request.item.ItemPutRequest;
import com.ssafy.api.response.item.ItemPutResponse;
import com.ssafy.api.service.basket.BasketService;
import com.ssafy.api.service.item.ItemService;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.exception.handler.RestTemplateException;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.Store;
import com.ssafy.db.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PutMapping("/")
	public ResponseEntity<? super ItemPutResponse> putItemIntBasket(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@RequestBody BasketPutRequest basketPutRequest) {
		try {
			User user = restUtil.getUserByToken(token);
			Item item = itemService.getItemByItemId(basketPutRequest.getItemId());
//
			basketService.putItemInBasket(user, item, basketPutRequest.getAmount());
		} catch (AuthException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				       .body(ItemPutResponse.of(401, ex.getMessage()));
		} catch (ResourceNotFoundException | RestTemplateException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				       .body(ItemPutResponse.of(404, ex.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(ItemPutResponse.of(200, "Success"));
	}
}
