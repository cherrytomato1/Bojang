package com.ssafy.api.response.item;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.Store;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemListGetResponse extends BaseResponseBody {

	List<Item> itemList;

	public static ItemListGetResponse of(Integer statusCode, String message, List<Item> list) {
		ItemListGetResponse itemListGetResponse = new ItemListGetResponse();
		itemListGetResponse.setStatusCode(statusCode);
		itemListGetResponse.setMessage(message);
		itemListGetResponse.setItemList(list);
		return itemListGetResponse;
	}
}
