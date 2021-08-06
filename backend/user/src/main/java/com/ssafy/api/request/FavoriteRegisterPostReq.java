package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FavoriteRegisterPostReq")
public class FavoriteRegisterPostReq {

    @ApiModelProperty(name = "유저ID")
    String userId;
    @ApiModelProperty(name = "가게ID")
    String storeId;

}
