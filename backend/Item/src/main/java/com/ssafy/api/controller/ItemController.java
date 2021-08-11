package com.ssafy.api.controller;

import com.ssafy.api.request.item.ItemPatchRequest;
import com.ssafy.api.request.item.ItemPutRequest;
import com.ssafy.api.response.item.ItemDeleteResponse;
import com.ssafy.api.response.item.ItemListGetResponse;
import com.ssafy.api.response.item.ItemPatchResponse;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/api/item")
@Slf4j
@RequiredArgsConstructor
public class ItemController {

	final RestUtil restUtil;

	final ItemService itemService;

	final StoreService storeService;

	@ApiOperation(value = "판매 상품 put", notes = "판매 상품 추가", response =
		                                                       ItemPutResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping("/{storeId}")
	public ResponseEntity<? super ItemPutResponse> putItem(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@ApiParam(value = "추가할 상품 정보", required = true) @RequestBody ItemPutRequest itemPutRequest,
		@ApiParam(value = "추가할 상점 ID", required = true) @PathVariable("storeId") String storeId) {
		try {
			String userId = restUtil.getUserId(token);
//			Store targetStore = storeService.getStoreByUserId(userId);
			Store targetStore = restUtil.getStoreByStoreId(storeId);
//          현재 store 서버에서 USER 반환하지 않음
//			storeService.validateStoreByUserId(userId, targetStore);
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

	@ApiOperation(value = "판매 상품 Delete", notes = "판매 상품 삭제", response =
		                                                          ItemDeleteResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@DeleteMapping("/{itemId}")
	public ResponseEntity<? super ItemDeleteResponse> deleteItem(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@ApiParam(value = "삭제할 아이템 ID", required = true) @PathVariable("itemId") String itemId) {
		try {
			String userId = restUtil.getUserId(token);
			itemService.deleteItemByItemId(itemId, userId);
		} catch (AuthException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				       .body(ItemDeleteResponse.of(401, ex.getMessage()));
		} catch (ResourceNotFoundException | RestTemplateException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				       .body(ItemDeleteResponse.of(404, ex.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(ItemDeleteResponse.of(200, "Success"));
	}

	@ApiOperation(value = "판매 상품 Update", notes = "판매 상품 업데이트", response =
		                                                            ItemPatchResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PatchMapping("/{storeId}")
	public ResponseEntity<? super ItemPatchResponse> updateItem(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@ApiParam(value = "업데이트할 상품 정보", required = true) @RequestBody ItemPatchRequest itemPatchRequest,
		@ApiParam(value = "상점 ID", required = true) @PathVariable("storeId") String storeId) {
		try {
			String userId = restUtil.getUserId(token);
			Store targetStore = restUtil.getStoreByStoreId(storeId);

//			storeService.validateStoreByUserId(userId, targetStore);
			itemService.patchItemInStore(itemPatchRequest, targetStore);
		} catch (AuthException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				       .body(ItemPatchResponse.of(401, ex.getMessage()));
		} catch (ResourceNotFoundException | RestTemplateException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				       .body(ItemPatchResponse.of(404, ex.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(ItemPatchResponse.of(200, "Success"));
	}

	@ApiOperation(value = "판매 상품 리스트 Get", notes = "판매 상품 리스트 받기", response =
		                                                               ItemListGetResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found")
	})

	@GetMapping("/{storeId}")
	public ResponseEntity<? super ItemListGetResponse> getItemList(
		@ApiParam(value = "조회할 상점 ID", required = true) @PathVariable("storeId") String storeId) {
		List<Item> itemList;
		try {
			itemList = itemService.getItemListByStoreId(storeId);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				       .body(ItemListGetResponse.of(404, "가게 조회 실패", null));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				       .body(BaseResponseBody.of(400, "아이템 목록 조회 실패"));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(ItemListGetResponse.of(200, "Success", itemList));
	}
}
