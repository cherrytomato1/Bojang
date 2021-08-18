package com.ssafy.api.request.order;

import com.ssafy.common.model.dto.OrderItemRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Billing Post Request")
public class OrderPostRequest {
	@ApiModelProperty(name = "결제할 사용자 ID", required = true)
	String userId;

	@ApiModelProperty(name = "결제 타입", required = true)
	Long payTypeId;

	@ApiModelProperty(name = "결제할 아이템 리스트", required = true)
	List<OrderItemRequestDto> orderItemList;

}
