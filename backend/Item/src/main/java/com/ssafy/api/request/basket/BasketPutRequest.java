package com.ssafy.api.request.basket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BasketPutRequest")
public class BasketPutRequest {
	@ApiModelProperty(name = "상품 아이디")
	String itemId;

	@ApiModelProperty(name = "상품 수량", example = "1")
	Long amount;
}
