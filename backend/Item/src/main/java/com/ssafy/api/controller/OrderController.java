package com.ssafy.api.controller;


import com.ssafy.api.request.order.OrderPostRequest;
import com.ssafy.api.response.basket.BasketPutResponse;
import com.ssafy.api.response.item.ItemDeleteResponse;
import com.ssafy.api.response.order.OrderPostResponse;
import com.ssafy.api.service.item.ItemService;
import com.ssafy.api.service.order.OrderService;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.exception.handler.RestTemplateException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Item;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

	@ApiOperation(value = "결제", notes = "결제하기", response =
		                                                             OrderPostResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 409, message = "Conflict")
	})

	@PostMapping("")
	public ResponseEntity<? extends BaseResponseBody> billOrders(
		@ApiIgnore @RequestHeader("Authorization") String token,
		@ApiParam(value = "결제할 상품 정보", required = true) @RequestBody OrderPostRequest orderPostRequest) {
		try {
			orderService.billOrders(orderPostRequest, token);
		} catch (AuthException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				       .body(OrderPostResponse.of(401, ex.getMessage()));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				       .body(OrderPostResponse.of(404, ex.getMessage()));
		} catch (RestTemplateException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
				       .body(OrderPostResponse.of(409, ex.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK)
			       .body(ItemDeleteResponse.of(200, "Success"));
	}
}
