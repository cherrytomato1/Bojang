package com.ssafy.api.response.store;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Store;
import com.ssafy.db.mapping.store.StoreMapping;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreListGetResponse extends BaseResponseBody {

    List<StoreMapping> storeList;

    public static StoreListGetResponse of(Integer statusCode, String message, List<StoreMapping> list) {
        StoreListGetResponse storeListGetResponse = new StoreListGetResponse();
        storeListGetResponse.setStatusCode(statusCode);
        storeListGetResponse.setMessage(message);
        storeListGetResponse.setStoreList(list);
        return storeListGetResponse;
    }


}
