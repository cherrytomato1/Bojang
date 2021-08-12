package com.ssafy.api.request.orderinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("OrderInfoPatchRequest")
public class OrderInfoPatchRequest {
    @ApiModelProperty(name = "주문 목록 ID")
    String orderInfoId;
    @ApiModelProperty(name = "주문 목록 상태 ID",example = "1 = 결제 완료, 2 = 픽업 완료, 3 = 인수 완료")
    Long orderStatusId;
}
