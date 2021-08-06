package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FavoriteRegisterPostRequest")
public class FavoriteRegisterPostRequest {

    @ApiModelProperty(name = "가게ID")
    String storeId;

}
