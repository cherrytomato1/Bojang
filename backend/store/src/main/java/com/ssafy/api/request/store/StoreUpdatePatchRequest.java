package com.ssafy.api.request.store;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class StoreUpdatePatchRequest {
    String storeId;
    String userId;
    String comment;
    String address;
    String name;
    MultipartFile file;
}
