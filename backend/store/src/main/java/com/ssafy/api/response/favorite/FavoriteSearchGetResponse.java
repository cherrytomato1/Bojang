package com.ssafy.api.response.favorite;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.FavoriteStore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("FavoriteSearchGetResponse")
public class FavoriteSearchGetResponse extends BaseResponseBody {
    @ApiModelProperty("검색된 관심 가게 리스트")
    List<FavoriteStore> list;

    public static FavoriteSearchGetResponse of(Integer statusCode, String message, List<FavoriteStore> list ){
        FavoriteSearchGetResponse favoriteSearchGetResponse = new FavoriteSearchGetResponse();
        favoriteSearchGetResponse.setStatusCode(statusCode);
        favoriteSearchGetResponse.setMessage(message);
        favoriteSearchGetResponse.setList(list);
        return favoriteSearchGetResponse;
    }
}
