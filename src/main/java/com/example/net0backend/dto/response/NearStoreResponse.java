package com.example.net0backend.dto.response;

import com.example.net0backend.entity.Shop;
import com.example.net0backend.enums.StoreStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class NearStoreResponse {
    private Long id;
    private StoreStatus status; // 남아요, 없어요
    private Double x;
    private Double y;

    @Builder
    private NearStoreResponse(Long id, StoreStatus status, Double x, Double y) {
        this.id = id;
        this.status = status;
        this.x = x;
        this.y = y;
    }

    public static NearStoreResponse from(Shop shop) {
        return NearStoreResponse.builder()
                .id(shop.getId())
                .status(shop.getStatus())
                .x(shop.getLatitude())
                .y(shop.getLongitude())
                .build();
    }
}
