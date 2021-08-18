package com.ssafy.api.request.sales;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SalesUpdatePutRequest")
public class SalesUpdatePutRequest {

	@ApiParam(value = "가게 아이디", required = true)
	String storeId;
	@ApiParam(value = "판매 금액", example = "3000", required = true)
	Long sum;
}
