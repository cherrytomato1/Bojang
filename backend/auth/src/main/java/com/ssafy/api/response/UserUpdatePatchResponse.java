package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 정보 업데이트 API ([PATCH] /api/user/update) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserGetResponse")
public class UserUpdatePatchResponse extends BaseResponseBody{
}
