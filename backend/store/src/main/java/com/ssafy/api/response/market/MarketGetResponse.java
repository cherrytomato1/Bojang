package com.ssafy.api.response.market;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Market;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("시장 정보")
public class MarketGetResponse extends BaseResponseBody {

    @ApiModelProperty("모든 시장 리스트")
    List<Market> marketList;

    public static MarketGetResponse of(Integer statusCode, String message, List<Market> list) {
        MarketGetResponse marketGetResponse = new MarketGetResponse();
        marketGetResponse.setStatusCode(statusCode);
        marketGetResponse.setMessage(message);
        marketGetResponse.setMarketList(list);
        return marketGetResponse;
    }
}
