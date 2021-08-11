package com.ssafy.api.service.order;

import com.ssafy.api.request.order.OrderPostRequest;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.dto.OrderItemRequestDto;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.OrderInfo;
import com.ssafy.db.entity.OrderItem;
import com.ssafy.db.entity.OrderStatus;
import com.ssafy.db.entity.PayType;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ItemRepository;
import com.ssafy.db.repository.OrderInfoRepository;
import com.ssafy.db.repository.OrderItemRepository;
import com.ssafy.db.repository.OrderStatusRepository;
import com.ssafy.db.repository.PayTypeRepository;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	final PayTypeRepository payTypeRepository;

	final OrderStatusRepository orderStatusRepository;

	final OrderInfoRepository orderInfoRepository;

	final OrderItemRepository orderItemRepository;

	final ItemRepository itemRepository;

	final RestUtil restUtil;

	@Transactional
	@Override
	public void billOrders(OrderPostRequest orderPostRequest, String token) {

		User user = restUtil.getUserByToken(token);

		if (!user.getId().equals(orderPostRequest.getUserId())) {
			throw new AuthException("사용자 ID가 일치하지 않습니다.");
		}

		OrderInfo orderInfo = OrderInfo.builder().user(user).orderItemList(new ArrayList<>())
			                      .payType(getPayTypeById(orderPostRequest.getPayTypeId()))
			                      .orderStatus(getNewOrderStatus()).build();

		int totalPrice = 0;
		for (OrderItemRequestDto orderItemDto : orderPostRequest.getOrderItemList()) {
			Item item = getItemByOrderItemId(orderItemDto.getItemId());

			OrderItem orderItem = new OrderItem();
			//addOrderItem에서 수행됨
//			orderItem.setOrderInfo(orderInfo);
			orderItem.setComment(orderItemDto.getComment());
			orderItem.setItem(item);
			orderItem.setPickStatus(false);
			orderItemRepository.save(orderItem);

			orderInfo.addOrderItem(orderItem);
			totalPrice += orderItemDto.getAmount() * item.getPrice();
		}

		orderInfo.setPrice(totalPrice);

		orderInfo = orderInfoRepository.save(orderInfo);
		restUtil.sendBillingRequestByOrderInfoId(orderInfo.getId(), token);
	}

	private PayType getPayTypeById(Long payTypeId) {
		return payTypeRepository.findById(payTypeId)
			       .orElseThrow(() -> new ResourceNotFoundException("PayType", "id", payTypeId));
	}

	private OrderStatus getNewOrderStatus() {
		return orderStatusRepository.findById(1L)
			       .orElseThrow(() -> new ResourceNotFoundException("OrderStatus", "id", "1"));
	}

	private Item getItemByOrderItemId(String orderItemId) {
		return itemRepository.findById(orderItemId).orElseThrow(() -> new ResourceNotFoundException("Item", "OrderItemId", orderItemId));
	}
}
