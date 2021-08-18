package com.ssafy.db.mapping.store;

import com.ssafy.db.entity.Market;
import com.ssafy.db.entity.StoreType;

public interface StoreMapping {
    String getId();

    String getName();

    String getComment();

    String getImage();

    Market getMarket();

    StoreType getStoreType();

    String getAddress();

    Integer getSection();
}
