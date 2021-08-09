package com.ssafy.api.response.sales;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Sales;
import com.ssafy.db.mapping.sales.SalesMapping;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("SalesGetListResponse")
public class SalesGetListResponse extends BaseResponseBody {
    @ApiModelProperty(value = "판매 금액 list")
    List<SalesMapping> salesList;

    public static SalesGetListResponse of(Integer statusCode, String message, List<SalesMapping> list) {
        SalesGetListResponse salesGetListResponse = new SalesGetListResponse();
        salesGetListResponse.setStatusCode(statusCode);
        salesGetListResponse.setMessage(message);
        salesGetListResponse.setSalesList(list);
        return salesGetListResponse;
    }
}
