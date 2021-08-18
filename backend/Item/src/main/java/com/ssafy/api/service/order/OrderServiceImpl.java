package com.ssafy.api.service.order;

import com.ssafy.api.request.order.OrderPostRequest;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.dto.OrderItemRequestDto;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Basket;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.OrderInfo;
import com.ssafy.db.entity.OrderItem;
import com.ssafy.db.entity.OrderStatus;
import com.ssafy.db.entity.PayType;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.BasketRepository;
import com.ssafy.db.repository.ItemRepository;
import com.ssafy.db.repository.OrderInfoRepository;
import com.ssafy.db.repository.OrderItemRepository;
import com.ssafy.db.repository.OrderStatusRepository;
import com.ssafy.db.repository.PayTypeRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

	final BasketRepository basketRepository;

	final RestUtil restUtil;

	@Transactional
	@Override
	public void billOrders(OrderPostRequest orderPostRequest, String token) {

		User user = restUtil.getUserByToken(token);
		Map<String, Long> storeSaleAmountMap = new HashMap<>();

		if (!user.getId().equals(orderPostRequest.getUserId())) {
			throw new AuthException("사용자 ID가 일치하지 않습니다.");
		}

		OrderInfo orderInfo = OrderInfo.builder().user(user).orderItemList(new ArrayList<>())
			                      .payType(getPayTypeById(orderPostRequest.getPayTypeId()))
			                      .orderStatus(getNewOrderStatus()).build();

		int totalPrice = 0;
		for (OrderItemRequestDto orderItemDto : orderPostRequest.getOrderItemList()) {
			//주문 내역에서 아이템 id 찾아오기
			Item item = getItemByOrderItemId(orderItemDto.getItemId());

			OrderItem orderItem = setOrderItemAndSave(orderItemDto, item);

			//아이템에서 스토어 아이디 찾기
			String storeId = item.getStore().getId();
			System.out.println(storeId);
			restUtil.getStoreByStoreId(storeId);
			//해당 상품 최종 구매금액
			Long orderItemPrice = orderItemDto.getAmount() * item.getPrice();

			//해당 주문에서 스토어 판매금액 합
			storeSaleAmountMap
				.put(storeId, storeSaleAmountMap.getOrDefault(storeId, 0L) + orderItemPrice);

			orderInfo.addOrderItem(orderItem);

			deleteBasketByItemAndUser(item, user);

			totalPrice += orderItemPrice;
		}

		orderInfo.setPrice(totalPrice);
		orderInfo = orderInfoRepository.save(orderInfo);

		storeSaleAmountMap.forEach((key, value) -> restUtil.addStoreSalePrice(value, key));
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
		return itemRepository.findById(orderItemId).orElseThrow(
			() -> new ResourceNotFoundException("Item", "OrderItemId", orderItemId));
	}

	private OrderItem setOrderItemAndSave(OrderItemRequestDto orderItemRequestDto, Item item) {
		OrderItem orderItem = new OrderItem();
		//addOrderItem에서 수행됨
//			orderItem.setOrderInfo(orderInfo);
		orderItem.setComment(orderItemRequestDto.getComment());
		orderItem.setItem(item);
		orderItem.setPickStatus(false);
		orderItem.setAmount(orderItemRequestDto.getAmount());
		return orderItemRepository.save(orderItem);
	}

	private void deleteBasketByItemAndUser(Item item, User user) {
		Optional<Basket> targetBasket = basketRepository
			                                .findByItem_IdAndUser_Id(item.getId(), user.getId());
//		Optional<Basket> targetBasket = basketRepository.findByItemAndUser(item, user);
		if (!targetBasket.isPresent()) {
			return;
		}
		basketRepository.delete(targetBasket.get());
	}
}
