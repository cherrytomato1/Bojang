package com.ssafy.api.response.store;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Store;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreGetResponse extends BaseResponseBody {
    @ApiModelProperty(name = "스토어 정보")
    Store store;

    public static StoreGetResponse of(Integer statusCode,String message,Store store){
        StoreGetResponse storeGetResponse = new StoreGetResponse();
        storeGetResponse.setStatusCode(statusCode);
        storeGetResponse.setMessage(message);
        storeGetResponse.setStore(store);
        return storeGetResponse;
    }
}
