package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.AuthUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 OAuth 프로필 요청 API ([GET] /api/user/userid) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("AuthUserGetResponse")
public class AuthUserProfileGetResponse extends BaseResponseBody{
	@ApiModelProperty(name="auth user")
	AuthUser authUser;
	
	public static AuthUserProfileGetResponse of(Integer statusCode, String message, AuthUser authUser) {
		AuthUserProfileGetResponse res = new AuthUserProfileGetResponse();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setAuthUser(authUser);
		return res;
	}
}
