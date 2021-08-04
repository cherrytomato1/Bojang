package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 로그인 API ([POST] /api/v1/auth) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class UserIdGetResponse extends BaseResponseBody{
	@ApiModelProperty(name="user entity")
	String userId;
	
	public static UserIdGetResponse of(Integer statusCode, String message, String userId) {
		UserIdGetResponse res = new UserIdGetResponse();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setUserId(userId);
		return res;
	}
}
