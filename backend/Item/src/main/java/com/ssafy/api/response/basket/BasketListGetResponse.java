package com.ssafy.api.response.basket;

import com.ssafy.common.model.dto.BasketResponseDto;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Basket;
import com.ssafy.db.entity.Item;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketListGetResponse extends BaseResponseBody {

	List<BasketResponseDto> BasketList;

	public static BasketListGetResponse of(Integer statusCode, String message, List<BasketResponseDto> list) {
		BasketListGetResponse itemListGetResponse = new BasketListGetResponse();
		itemListGetResponse.setStatusCode(statusCode);
		itemListGetResponse.setMessage(message);
		itemListGetResponse.setBasketList(list);
		return itemListGetResponse;
	}
}
