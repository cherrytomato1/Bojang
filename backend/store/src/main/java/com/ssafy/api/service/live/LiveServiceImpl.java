package com.ssafy.api.service.live;

import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.response.dto.StoreLiveDto;
import com.ssafy.db.entity.Store;
import com.ssafy.db.entity.StoreLive;
import com.ssafy.db.repository.StoreLiveRepository;
import com.ssafy.db.repository.StoreRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LiveServiceImpl implements LiveService {

    @Autowired
    StoreLiveRepository storeLiveRepository;

    @Autowired
    StoreRepositiory storeRepositiory;

    @Override
    public StoreLive getStoreLiveByStoreId(String storeId) {
        StoreLive storeLive = storeLiveRepository.findByStore_Id(storeId).orElseThrow(() -> new ResourceNotFoundException("storeId", "Id", storeId));
        return storeLive;
    }

    @Override
    public StoreLive createStoreLive(String storeId) {
        Optional<StoreLive> storeLiveOptional = storeLiveRepository.findByStore_Id(storeId);

        if (storeLiveOptional.isPresent()) {
            return null;
        }
        StoreLive storeLive = new StoreLive();
        Store store = storeRepositiory.findById(storeId).orElseThrow(() -> new ResourceNotFoundException("storeId", "Id", storeId));
        storeLive.setStore(store);
        return storeLiveRepository.save(storeLive);

    }

    @Override
    public void deleteStoreLive(String storeId) {
        StoreLive storeLive = storeLiveRepository.findByStore_Id(storeId).orElseThrow(() -> new ResourceNotFoundException("storeId", "Id", storeId));
        storeLiveRepository.delete(storeLive);
    }

    @Override
    public List<StoreLiveDto> getLiveList() {
        List<StoreLive> liveList = storeLiveRepository.findAll();
        List<StoreLiveDto> storeLiveDtoList = new ArrayList<>();
        for (StoreLive live : liveList) {
            StoreLiveDto storeLiveDto = new StoreLiveDto();
            storeLiveDto.setStoreId(live.getStore().getId());
            storeLiveDto.setStoreName(live.getStore().getName());
            storeLiveDtoList.add(storeLiveDto);
        }
        return storeLiveDtoList;
    }
}
