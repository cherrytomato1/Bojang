package com.ssafy.api.controller;


import com.ssafy.api.request.order.OrderPostRequest;
import com.ssafy.api.response.item.ItemDeleteResponse;
import com.ssafy.api.service.item.ItemService;
import com.ssafy.api.service.order.OrderService;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.exception.handler.RestTemplateException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Item;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/billing")
public class OrderController {

	final RestUtil restUtil;

	final ItemService itemService;

	final OrderService orderService;

	@PostMapping("")
	public ResponseEntity<? extends BaseResponseBody> billOrders(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@ApiParam(value = "추가할 상품 정보", required = true) @RequestBody OrderPostRequest orderPostRequest) {
		try {
			orderService.billOrders(orderPostRequest, token);
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
}
