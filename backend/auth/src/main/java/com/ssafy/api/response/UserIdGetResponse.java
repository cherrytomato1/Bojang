package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 ID 요청 API ([GET] /api/user/userid) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserIdGetResponse")
public class UserIdGetResponse extends BaseResponseBody{
	@ApiModelProperty(name="userid")
	String userId;
	
	public static UserIdGetResponse of(Integer statusCode, String message, String userId) {
		UserIdGetResponse res = new UserIdGetResponse();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setUserId(userId);
		return res;
	}
}
