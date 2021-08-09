package com.ssafy.api.response.favorite;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.FavoriteStore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FavoriteRegisterPostResponse")
public class FavoriteRegisterPostResponse extends BaseResponseBody {

    @ApiModelProperty("등록된 관심가게")
    FavoriteStore favoriteStore;

    public static FavoriteRegisterPostResponse of(Integer statusCode, String message,FavoriteStore favoriteStore ){
        FavoriteRegisterPostResponse response = new FavoriteRegisterPostResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setFavoriteStore(favoriteStore);
        return response;
    }
}
