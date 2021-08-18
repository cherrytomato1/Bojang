package com.ssafy.api.service.order;

import static org.junit.jupiter.api.Assertions.*;

import com.ssafy.db.entity.Basket;
import com.ssafy.db.repository.BasketRepository;
import com.ssafy.db.repository.OrderItemRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderServiceImplTest {

	@Autowired
	BasketRepository basketRepository;

	@Test
	public void 아이템아이디와_사용자아이디로_장바구니를_삭제한다() {
		String itemId = "S0212fd45e229";
		String userId = "Sb7bf7830e855";
		Optional<Basket> targetBasket = basketRepository.findByItem_IdAndUser_Id(itemId, userId);

		if (!targetBasket.isPresent()) {
			System.out.println(itemId + " " + userId + " is null");
			return;
		}
		basketRepository.delete(targetBasket.get());
	}

}