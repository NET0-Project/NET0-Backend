package com.example.net0backend.dto.response;

import com.example.net0backend.entity.Shop;
import lombok.Builder;
import lombok.Data;

@Data
public class StoreInfoResponse {
    private Long storeId;
    private String storeName;
    private String storeType;
    private String openTime;
    private Integer distance;

    @Builder
    private StoreInfoResponse(Long storeId, String storeName, String storeType, String openTime, Integer distance) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeType = storeType;
        this.openTime = openTime;
        this.distance = distance;
    }

    public static StoreInfoResponse of(Shop shop, Integer distance){
        return StoreInfoResponse.builder()
                .storeId(shop.getId())
                .storeName(shop.getName())
                .storeType(shop.getType())
                .openTime(shop.getOpeningHour())
                .distance(distance)
                .build();
    }

}
