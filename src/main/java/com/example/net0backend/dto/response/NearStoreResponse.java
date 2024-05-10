package com.example.net0backend.dto.response;

import com.example.net0backend.entity.Shop;
import com.example.net0backend.enums.StoreStatus;
import lombok.Data;

@Data
public class NearStoreResponse {
    private Long id;
    private StoreStatus status; // 남아요, 없어요
    private Double x;
    private Double y;

    public NearStoreResponse from(Shop shop) {
        this.id = shop.getId();
        this.status = shop.getStatus();
        this.x = shop.getLatitude();
        this.y = shop.getLongitude();
        return this;
    }
}
