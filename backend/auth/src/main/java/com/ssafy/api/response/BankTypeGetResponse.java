package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.BankType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 은행 정보 요청 API ([GET] /api/user/banktype) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("BankTypeGetResponse")
public class BankTypeGetResponse extends BaseResponseBody {
    @ApiModelProperty(name = "bank list")
    List<BankType> bankTypeList;

    public static BankTypeGetResponse of(Integer statusCode, String message, List<BankType> bankTypeList) {
        BankTypeGetResponse response = new BankTypeGetResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setBankTypeList(bankTypeList);
        return response;
    }
}
