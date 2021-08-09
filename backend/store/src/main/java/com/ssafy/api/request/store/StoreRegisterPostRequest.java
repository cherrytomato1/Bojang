package com.ssafy.api.request.store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRegisterPostRequest {
    String adress;
    String image;
    String name;
    String commnet;

}
