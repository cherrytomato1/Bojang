package com.ssafy.api.response.sales;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel("SalesGetResponse")
public class SalesGetResponse extends BaseResponseBody {
    @ApiModelProperty(value = "날자")
    LocalDate localDate;
    @ApiModelProperty(value = "판매 금액")
    Integer amount;

    public static SalesGetResponse of(Integer statusCode, String message, LocalDate localDate, Integer amount) {
        SalesGetResponse salesGetResponse = new SalesGetResponse();
        salesGetResponse.setStatusCode(statusCode);
        salesGetResponse.setMessage(message);
        salesGetResponse.setLocalDate(localDate);
        salesGetResponse.setAmount(amount);
        return salesGetResponse;
    }
}
