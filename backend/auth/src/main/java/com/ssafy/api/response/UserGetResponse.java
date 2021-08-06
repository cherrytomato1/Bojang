package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 정보 요청 API ([GET] /api/user/info) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserGetResponse")
public class UserGetResponse extends BaseResponseBody{
	@ApiModelProperty(name="user entity")
	User user;
	
	public static UserGetResponse of(Integer statusCode, String message, User user) {
		UserGetResponse res = new UserGetResponse();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setUser(user);
		return res;
	}
}
