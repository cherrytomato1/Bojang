package com.ssafy.api.response.live;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.model.response.dto.StoreLiveDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StoreLiveGetResponse extends BaseResponseBody {
    StoreLiveDto storeLiveDto;

    public static StoreLiveGetResponse of(Integer statusCode, String message, StoreLiveDto list) {
        StoreLiveGetResponse response = new StoreLiveGetResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setStoreLiveDto(list);
        return response;
    }
}
