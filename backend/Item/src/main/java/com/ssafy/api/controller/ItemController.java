package com.ssafy.api.controller;

import com.ssafy.api.request.item.ItemPutRequest;
import com.ssafy.api.response.item.ItemDeleteResponse;
import com.ssafy.api.response.item.ItemListGetResponse;
import com.ssafy.api.response.item.ItemPutResponse;
import com.ssafy.api.service.item.ItemService;
import com.ssafy.api.service.store.StoreService;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.exception.handler.RestTemplateException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.Store;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/item")
@Slf4j
@RequiredArgsConstructor
public class ItemController {

	final RestUtil restUtil;

	final ItemService itemService;

	final StoreService storeService;

	@PutMapping("/{storeId}")
	public ResponseEntity<? super ItemPutResponse> putItem(
		@ApiIgnore @RequestHeader("Authorization") String token, @RequestBody ItemPutRequest itemPutRequest,
		@PathVariable("storeId") String storeId) {
		try {
			String userId = restUtil.getUserId(token);
//			Store targetStore = storeService.getStoreByUserId(userId);
			Store targetStore = restUtil.getStoreByStoreId(storeId);

			storeService.validateStoreId(storeId, targetStore);
			itemService.putItemInStore(itemPutRequest, targetStore);
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

	@DeleteMapping("/{itemId}")
	public ResponseEntity<? super ItemDeleteResponse> deleteItem(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@PathVariable("itemId") String itemId) {
		try {
			restUtil.getUserId(token);
			itemService.deleteItemByItemId(itemId);
		} catch (AuthException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				       .body(ItemDeleteResponse.of(401, ex.getMessage()));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				       .body(ItemDeleteResponse.of(404, ex.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(ItemDeleteResponse.of(200, "Success"));
	}

	@PatchMapping("/{storeId}")
	public ResponseEntity<? super ItemPutResponse> updateItem(
		@ApiIgnore @RequestHeader("Authorization") String token, @RequestBody ItemPutRequest itemPutRequest,
		@PathVariable("storeId") String storeId) {
		try {
			String userId = restUtil.getUserId(token);
//			Store targetStore = storeService.getStoreByUserId(userId);
			Store targetStore = restUtil.getStoreByStoreId(storeId);

			storeService.validateStoreId(storeId, targetStore);
			itemService.putItemInStore(itemPutRequest, targetStore);
		} catch (AuthException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				       .body(ItemPutResponse.of(401, ex.getMessage()));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				       .body(ItemPutResponse.of(404, ex.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(ItemPutResponse.of(200, "Success"));
	}

	@GetMapping("/{storeId}")
	public ResponseEntity<? super ItemListGetResponse> getItemList(
		@PathVariable("storeId") String storeId) {
		List<Item> itemList;
		try {
			itemList = itemService.getItemListByStoreId(storeId);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				       .body(ItemListGetResponse.of(404, "아이템 목록 조회 실패", null));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				       .body(BaseResponseBody.of(400, "아이템 목록 조회 실패"));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(ItemListGetResponse.of(200, "Success", itemList));
	}
}
