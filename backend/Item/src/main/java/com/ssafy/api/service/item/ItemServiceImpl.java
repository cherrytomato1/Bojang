package com.ssafy.api.service.item;

import com.ssafy.api.request.item.ItemPatchRequest;
import com.ssafy.api.request.item.ItemPutRequest;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.FileDownloadException;
import com.ssafy.common.exception.handler.FileUploadException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.config.FileUploadConfig;
import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.ItemType;
import com.ssafy.db.entity.Store;
import com.ssafy.db.repository.ItemRepository;
import com.ssafy.db.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {
    final Path fileLocation;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemTypeRepository itemTypeRepository;


    @Autowired
    public ItemServiceImpl(FileUploadConfig prop) {
        this.fileLocation = Paths.get(prop.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

    @Override
    public List<Item> getItemListByStoreId(String storeId) {
        return itemRepository.findByStoreId(storeId);
    }

    @Override
    public Item getItemByItemId(String itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "Id", itemId));

    }

    @Transactional
    @Override
    public void putItemInStore(ItemPutRequest itemPutRequest, Store targetStore) {
        if (itemPutRequest.getFile() != null) {
            UUID uuid = UUID.randomUUID();
            MultipartFile file = itemPutRequest.getFile();
            String fileName = uuid + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            try {
                // 파일명에 부적합 문자가 있는지 확인한다.
                if (fileName.contains(".."))
                    throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);
                Path targetLocation = this.fileLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                ItemType itemType = getItemTypeByItemTypeId(itemPutRequest.getItemType());
                Boolean onSale = itemPutRequest.getOnSale();
                Item item = Item.builder().name(itemPutRequest.getName())
                        .content(itemPutRequest.getContent())
                        .itemType(itemType).onSale(true).price(itemPutRequest.getPrice())
                        .store(targetStore).onSale(onSale == null ? false : onSale)
                        .image(fileName).build();
                itemRepository.save(item);
            } catch (Exception e) {
                throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도하십시오.", e);
            }
            throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도하십시오.");
        } else {
            ItemType itemType = getItemTypeByItemTypeId(itemPutRequest.getItemType());
            Boolean onSale = itemPutRequest.getOnSale();
            Item item = Item.builder().name(itemPutRequest.getName())
                    .content(itemPutRequest.getContent())
                    .itemType(itemType).onSale(true).price(itemPutRequest.getPrice())
                    .store(targetStore).onSale(onSale == null ? false : onSale)
                    .build();
            itemRepository.save(item);
            return;
        }

    }

    @Transactional
    @Override
    public void patchItemInStore(ItemPatchRequest itemPatchRequest, Store targetStore) {
        Item item = getItemByItemId(itemPatchRequest.getItemId());
        if (!item.getStore().equals(targetStore)) {
            throw new AuthException("가게에 등록되어 있는 아이템이 아닙니다.");
        }

        if(itemPatchRequest.getFile() != null){
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + StringUtils.cleanPath(itemPatchRequest.getFile().getOriginalFilename());
            try {
                // 파일명에 부적합 문자가 있는지 확인한다.
                if (fileName.contains(".."))
                    throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);
                Path targetLocation = this.fileLocation.resolve(fileName);
                Files.copy(itemPatchRequest.getFile().getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                item.setImage(fileName);
            }catch (Exception e) {
                throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도하십시오.", e);
            }
        }

        ItemType itemType = getItemTypeByItemTypeId(itemPatchRequest.getItemType());
        item.setItemType(itemType);
        item.setContent(itemPatchRequest.getContent());
        item.setName(itemPatchRequest.getName());
        item.setPrice(itemPatchRequest.getPrice());
        item.setOnSale(itemPatchRequest.getOnSale());

        itemRepository.save(item);
    }

    @Override
    public ItemType getItemTypeByItemTypeId(Long itemTypeId) {
        return itemTypeRepository.findById(itemTypeId).orElseThrow(
                () -> new ResourceNotFoundException("ItemType", "ItemTypeId", itemTypeId));
    }

    @Transactional
    @Override
    public void deleteItemByItemId(String itemId, String userId) {
        try {
            Item item = getItemByItemId(itemId);
            if (!userId.equals(item.getStore().getUser().getId())) {
                System.out.println(userId + " " + item.getStore().getUser().getId());
                throw new AuthException("삭제가 허가되지 않은 사용자/상품입니다");
            }
            itemRepository.delete(item);
//			itemRepository.deleteById(itemId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("item", "itemId", itemId);
        }
    }

    @Override
    public void itemImgUpload(MultipartFile file, String itemId, Store targetStore) {
        Item item = getItemByItemId(itemId);
        if (!item.getStore().equals(targetStore)) {
            throw new AuthException("가게에 등록되어 있는 아이템이 아닙니다.");
        }
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // 파일명에 부적합 문자가 있는지 확인한다.
            if (fileName.contains(".."))
                throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);
            Path targetLocation = this.fileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            item.setImage(fileName);
            itemRepository.save(item);
            return;
        } catch (Exception e) {
            throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도하십시오.", e);
        }
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
