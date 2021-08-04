package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 정보수정 API ([PATCH] /api/v1/<String:userId>) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserUpdatePatchRequest")
public class UserUpdatePatchRequest {
    @ApiModelProperty(name="유저 Department", example = "SSAFY")
    String department;
    @ApiModelProperty(name = "유저 Position", example = "교육생")
    String position;
    @ApiModelProperty(name = "유저 이름", example = "홍길동")
    String name;

}
