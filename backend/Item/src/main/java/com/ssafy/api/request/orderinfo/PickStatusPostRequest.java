package com.ssafy.api.request.orderinfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickStatusPostRequest {
    String orderItemId;
    boolean pickStatus;
}
