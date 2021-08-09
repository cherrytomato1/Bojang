package com.ssafy.api.controller;

import com.ssafy.api.response.favorite.FavoriteListGetResponse;
import com.ssafy.api.response.item.ItemListGetResponse;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.FavoriteStore;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
@Slf4j
@RequiredArgsConstructor
public class ItemController {

	RestUtil restUtil;

	@PostMapping("/")
	public ResponseEntity<?> getFavoriteList(@RequestHeader("Authorization") String token) {
		try {
			String id = restUtil.getUserId(token);
//			List<FavoriteStore> list = favoriteService.getFavoriteStore(id);
			List<FavoriteStore> list = null;

			return ResponseEntity.ok(
				FavoriteListGetResponse.of(HttpStatus.OK.value(), "Success", list));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(404).body(
				FavoriteListGetResponse.of(HttpStatus.NOT_FOUND.value(), "관심가게 리스트 조회 실패", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
		}
	}

	@GetMapping("/{store-id}")
	public ResponseEntity<? super ItemListGetResponse> getItemList(@PathVariable("store-id") String storeId) {
		return null;
	}
}
