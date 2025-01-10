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
    private String telephone;
    private int stock;

    @Builder
    private StoreInfoResponse(Long storeId, String storeName, String storeType, String openTime, Integer distance, String telephone, int stock) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeType = storeType;
        this.openTime = openTime;
        this.distance = distance;
        this.telephone = telephone;
        this.stock = stock;
    }

    public static StoreInfoResponse of(Shop shop, int stock, Integer distance) {
        return StoreInfoResponse.builder()
                .storeId(shop.getId())
                .storeName(shop.getName())
                .storeType(shop.getType())
                .openTime(shop.getOpeningHour())
                .distance(distance)
                .telephone(shop.getCall())
                .stock(stock)
                .build();
    }

}
