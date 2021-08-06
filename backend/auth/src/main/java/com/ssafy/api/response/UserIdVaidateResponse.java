package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 ID와 토큰 일치 여부 확인 API ([GET] /api/user/validateId) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserIdValidateResponse")
public class UserIdVaidateResponse extends BaseResponseBody{
}
