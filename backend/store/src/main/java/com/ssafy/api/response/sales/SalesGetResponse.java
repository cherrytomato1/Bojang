package com.ssafy.api.response.sales;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Sales;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SalesGetResponse extends BaseResponseBody {
    LocalDate localDate;
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
