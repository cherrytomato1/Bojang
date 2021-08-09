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
@ApiModel("FavoriteListGetResponse")
public class FavoriteListGetResponse extends BaseResponseBody {

    @ApiModelProperty(name="관심 가게 리스트")
    List<FavoriteStore> list;

    public static FavoriteListGetResponse of(Integer statusCode, String message, List<FavoriteStore> list){
        FavoriteListGetResponse response = new FavoriteListGetResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setList(list);
        return response;
    }
}
