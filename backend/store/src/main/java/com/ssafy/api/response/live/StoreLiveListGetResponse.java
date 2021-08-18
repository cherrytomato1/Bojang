package com.ssafy.api.response.live;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.model.response.dto.StoreLiveDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreLiveListGetResponse extends BaseResponseBody {
    List<StoreLiveDto> list;

    public static StoreLiveListGetResponse of(Integer statusCode, String message, List<StoreLiveDto> list) {
        StoreLiveListGetResponse response = new StoreLiveListGetResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setList(list);
        return response;
    }
}
