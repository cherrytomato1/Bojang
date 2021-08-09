package com.ssafy.api.response.store;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Store;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StorePostResponse")
public class StorePostResponse extends BaseResponseBody {
    @ApiModelProperty(value = "가게 정보")
    Store store;

    public static StorePostResponse of(Integer statusCode, String message, Store store) {
        StorePostResponse storePostResponse = new StorePostResponse();
        storePostResponse.setStatusCode(statusCode);
        storePostResponse.setMessage(message);
        storePostResponse.setStore(store);
        return storePostResponse;
    }
}
