package com.ssafy.api.request.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FavoriteRegisterPostReq")
public class FavoriteRegisterPostReq {

    @ApiModelProperty(name = "가게ID")
    String storeId;

}
