package com.ssafy.api.request.store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreCategoryGetRequest {
    Long marketId;
    Long storeTypeId;
}
