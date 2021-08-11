package com.ssafy.api.request.basket;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketIdListDeleteRequest {
	@ApiModelProperty(name = "장바구니 ID 리스트")
	List<String> basketIdList;
}
