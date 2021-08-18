package com.ssafy.api.service.live;

import com.ssafy.common.model.response.dto.StoreLiveDto;
import com.ssafy.db.entity.StoreLive;

import java.util.List;

public interface LiveService {
    StoreLive getStoreLiveByStoreId(String storeId);

    StoreLive createStoreLive(String storeId);

    void deleteStoreLive(String storeId);

    List<StoreLiveDto> getLiveList();
}
