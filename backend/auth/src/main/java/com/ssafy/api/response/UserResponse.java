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
public class UserResponse extends BaseResponseBody{
	@ApiModelProperty(name="user entity")
	User user;
	
	public static UserResponse of(Integer statusCode, String message, User user) {
		UserResponse res = new UserResponse();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setUser(user);
		return res;
	}
}
