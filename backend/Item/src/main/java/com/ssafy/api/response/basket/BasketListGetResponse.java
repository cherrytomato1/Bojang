package com.ssafy.api.response.basket;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Basket;
import com.ssafy.db.entity.Item;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketListGetResponse extends BaseResponseBody {

	List<Basket> BasketList;

	public static BasketListGetResponse of(Integer statusCode, String message, List<Basket> list) {
		BasketListGetResponse itemListGetResponse = new BasketListGetResponse();
		itemListGetResponse.setStatusCode(statusCode);
		itemListGetResponse.setMessage(message);
		itemListGetResponse.setBasketList(list);
		return itemListGetResponse;
	}
}
