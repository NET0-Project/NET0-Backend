package com.example.net0backend.dto.response;

import com.example.net0backend.enums.StoreStatus;
import lombok.Data;

@Data
public class NearStoreResponse {
    private StoreStatus status; // 남아요, 없어요
    private Double x;
    private Double y;
}
