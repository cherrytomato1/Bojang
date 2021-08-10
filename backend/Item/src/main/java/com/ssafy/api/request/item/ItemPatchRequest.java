package com.ssafy.api.request.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ItemPutRequest")
public class ItemPatchRequest {
	@ApiModelProperty(name = "상품 아이디")
	String itemId;

	@ApiModelProperty(name = "상품 이름", example = "사과")
	String name;

	@ApiModelProperty(name = "상품 설명", example = "싱싱한 사과입니다")
	String content;

	@ApiModelProperty(name = "상품 가격", example = "10000")
	Long price;

	@ApiModelProperty(name = "상품 이미지 url", example = "이미지 링크")
	String image;

	@ApiModelProperty(name = "상품 종류", required = true, example = "1")
	Long itemType;

	@ApiModelProperty(name = "판매중", example = "1")
	Boolean onSale;

}
