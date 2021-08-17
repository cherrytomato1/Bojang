package com.ssafy.api.request.store;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StoreCommentPostRequest")
public class StoreCommentPostRequest {
    String comment;
}
