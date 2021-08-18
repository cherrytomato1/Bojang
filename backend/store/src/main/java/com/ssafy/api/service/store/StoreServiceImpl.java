package com.ssafy.api.service.store;

import com.ssafy.common.exception.handler.FileDownloadException;
import com.ssafy.common.exception.handler.FileUploadException;
import com.ssafy.config.FileUploadConfig;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.Store;
import com.ssafy.db.mapping.store.StoreMapping;
import com.ssafy.db.repository.ItemRepository;
import com.ssafy.db.repository.StoreRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreServiceImpl implements StoreService {
    private final Path fileLocation;

    @Autowired
    StoreRepositiory storeRepositiory;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    public StoreServiceImpl(FileUploadConfig prop) {
        this.fileLocation = Paths.get(prop.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }


    @Override
    public Store getStore(String userId) {
        Optional<Store> storeOptional = storeRepositiory.findByUser_Id(userId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            return store;
        }
        return null;
    }

    @Override
    public Store getStoreInfo(String storeId) {
        Optional<Store> storeOptional = storeRepositiory.findById(storeId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            return store;
        }
        return null;
    }

    @Override
    public String getStoreId(String userId) {
        Optional<Store> storeOptional = storeRepositiory.findByUser_Id(userId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            return store.getId();
        }
        return null;
    }

    @Override
    public List<Item> getStoreItemList(String storeId) {
        Optional<Store> storeOptional = storeRepositiory.findById(storeId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            List<Item> itemList = store.getItemList();
            return itemList;
        }
        return null;
    }

    @Override
    public List<StoreMapping> searchStore(String storeName, Long MarketId) {
        List<StoreMapping> storeList = storeRepositiory.findByNameContainingAndMarket_Id(storeName, MarketId);
        return storeList;
    }

    @Override
    public String storeImgUpload(MultipartFile file, String userId) {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // 파일명에 부적합 문자가 있는지 확인한다.
            if (fileName.contains(".."))
                throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);
            Optional<Store> storeOptional = storeRepositiory.findByUser_Id(userId);
            if (storeOptional.isPresent()) {
                Path targetLocation = this.fileLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                Store store = storeOptional.get();
                store.setImage(fileName);
                storeRepositiory.save(store);
                return fileName;
            }
        } catch (Exception e) {
            throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도하십시오.", e);
        }
        throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도하십시오.");
    }

    @Override
    public List<StoreMapping> getStoreTypeList(Long marketId, Long storeTypeId) {
        return storeRepositiory.findByMarket_IdAndStoreType_Id(marketId, storeTypeId);
    }

    @Override
    public List<StoreMapping> getMarketList(Long marketId) {
        return storeRepositiory.findByMarket_Id(marketId);
    }

    @Override
    public Store updateComment(String userId, String comment) {
        Optional<Store> storeOptional = storeRepositiory.findByUser_Id(userId);
        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            store.setComment(comment);
            return storeRepositiory.save(store);
        }
        return null;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            }else {
                throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
            }
        }catch(MalformedURLException e) {
            throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
        }
    }
}
