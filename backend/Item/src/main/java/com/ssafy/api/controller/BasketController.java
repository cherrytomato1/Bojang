package com.ssafy.api.controller;

import com.ssafy.api.request.basket.BasketIdListDeleteRequest;
import com.ssafy.api.request.basket.BasketPatchRequest;
import com.ssafy.api.request.basket.BasketPutRequest;
import com.ssafy.api.response.basket.BasketDeleteResponse;
import com.ssafy.api.response.basket.BasketListGetResponse;
import com.ssafy.api.response.basket.BasketPatchResponse;
import com.ssafy.api.response.basket.BasketPutResponse;
import com.ssafy.api.response.item.ItemListGetResponse;
import com.ssafy.api.service.basket.BasketService;
import com.ssafy.api.service.item.ItemService;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.DuplicateBasketItemException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.exception.handler.RestTemplateException;
import com.ssafy.common.model.dto.BasketResponseDto;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Basket;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/basket")
@RequiredArgsConstructor
public class BasketController {

	final BasketService basketService;

	final ItemService itemService;

	final RestUtil restUtil;

	@ApiOperation(value = "장바구니 아이템 Put", notes = "장바구니 아이템 추가", response =
		                                                             BasketPutResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 409, message = "Conflict")
	})

	@PutMapping("")
	public ResponseEntity<? extends BaseResponseBody> putItemIntBasket(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@ApiParam(value = "장바구니에 추가할 상품 정보", required = true) @RequestBody BasketPutRequest basketPutRequest) {
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
		} catch (DuplicateBasketItemException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
				       .body(BasketPutResponse.of(409, ex.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(BasketPutResponse.of(200, "Success"));
	}

	@ApiOperation(value = "토큰 사용자 장바구니 리스트 GET", notes = "토큰에 저장되어 있는 사용자의 장바구니 리스트 반환", response =
		                                                                                     BasketListGetResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 409, message = "Conflict")
	})

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

	@ApiOperation(value = "장바구니 ID list로 해당 장바구니 삭제", notes = "장바구니 ID List로 해당 장바구니 아이템 Delete",
		response =
			BasketDeleteResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 409, message = "Conflict")
	})

	@DeleteMapping("")
	public ResponseEntity<? extends BaseResponseBody> deleteBasket(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@ApiParam(value = "삭제할 장바구니 ID리스트", required = true) @RequestBody BasketIdListDeleteRequest basketIdListDeleteRequest) {
		try {
			restUtil.getUserId(token);
			basketService.deleteBasketByBasketIdList(basketIdListDeleteRequest);
		} catch (AuthException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				       .body(BasketDeleteResponse.of(401, "사용자 인증 실패 !"));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
				       .body(BasketDeleteResponse.of(409, ex.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(BasketDeleteResponse.of(200, "Success"));
	}


	@ApiOperation(value = "장바구니 정보 수정", notes = "장바구니에 수량 정보등을 수정", response =
		                                                                BasketPatchResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 409, message = "Conflict")
	})
	@PatchMapping("")
	public ResponseEntity<? extends BaseResponseBody> updateBasket(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@ApiParam(value = "장바구니 Update 정보", required = true) @RequestBody BasketPatchRequest basketPatchRequest) {
		try {
			restUtil.getUserId(token);
			basketService.updateBasket(basketPatchRequest);
		} catch (AuthException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				       .body(BasketPatchResponse.of(401, "사용자 인증 실패 !"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				       .body(BasketPatchResponse.of(404, ex.getMessage()));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
				       .body(BasketPatchResponse.of(409, ex.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(BasketPatchResponse.of(200, "Success"));

	}
}
