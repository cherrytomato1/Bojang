package com.ssafy.common.exception.handler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateBasketItemException extends RuntimeException {

	private String itemId;
	private String itemName;
	private String userId;

	public DuplicateBasketItemException(String itemId, String itemName, String userId) {
		super(String.format("%s is already in %s's basket Id : '%s'", itemName, itemId, userId));
		this.itemId = itemId;
		this.itemName = itemName;
		this.userId = userId;
	}
}
