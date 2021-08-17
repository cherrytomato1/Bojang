package com.ssafy.api.service.order;

import com.ssafy.api.request.order.OrderPostRequest;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.User;
import java.util.List;

public interface OrderService {
	void billOrders(OrderPostRequest orderPostRequest, String token);
}
