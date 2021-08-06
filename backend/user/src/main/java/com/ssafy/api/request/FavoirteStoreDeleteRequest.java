package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FavoirteStoreDeleteRequest")
public class FavoirteStoreDeleteRequest {
    @ApiModelProperty("가게 아이디")
    String storeId;
}
