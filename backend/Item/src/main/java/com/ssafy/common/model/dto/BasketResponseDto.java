package com.ssafy.common.model.dto;

import com.ssafy.db.entity.Basket;
import com.ssafy.db.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketResponseDto {

	Basket basket;

	String storeId;

	String storeName;
}
