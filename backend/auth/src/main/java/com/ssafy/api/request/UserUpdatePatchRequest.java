package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 정보수정 API ([PATCH] /api/user/update) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserUpdatePatchRequest")
public class UserUpdatePatchRequest {

	@ApiModelProperty(name = "유저 Type ID, 1 = 구매자, 2 = 판매자, 3 = 픽업매니저", example = "1")
	Long userType;

	@ApiModelProperty(name = "판매자만, 은행 Type ID", example = "1")
	Long bankType;

//    @ApiModelProperty(name="유저 주소", example = "0")
//    String address;
//
//    @ApiModelProperty(name="유저 주소 상세", example = "0")
//    String addressDetail;

	@ApiModelProperty(name = "판매자만, 계좌번호", example = "1002-752-155448")
	String accountNumber;

	@ApiModelProperty(name = "유저 전화번호", example = "010-1234-1234")
	String phoneNumber;

	@ApiModelProperty(name = "유저 이름", example = "김민기")
	String name;

}
