package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 정보 삭제 ([DELETE] /api/user/delete) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserGetResponse")
public class UserDeleteResponse extends BaseResponseBody{
}
