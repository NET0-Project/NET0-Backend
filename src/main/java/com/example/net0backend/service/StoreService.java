package com.example.net0backend.service;

import com.example.net0backend.dto.request.MyLocationRequest;
import com.example.net0backend.dto.response.NearStoreResponse;
import com.example.net0backend.dto.response.StoreInfoResponse;
import com.example.net0backend.entity.Shop;
import com.example.net0backend.repository.ItemRepository;
import com.example.net0backend.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;

    private final double EARTH_RADIUS = 6371e3;

    public List<NearStoreResponse> getNearStoreList(MyLocationRequest myLocation) {
        List<Shop> nearShopList = shopRepository.findNearShopList(myLocation.getX(), myLocation.getY());
        List<NearStoreResponse> nearStoreResponseList = new ArrayList<>();
        nearShopList.stream().forEach(shop -> {
            nearStoreResponseList.add(NearStoreResponse.from(shop));
        });
        return nearStoreResponseList;
    }

    public Optional<Shop> getShopById(Long storeId){
        return shopRepository.findShopById(storeId);
    }

    public StoreInfoResponse getStoreInfo(MyLocationRequest myLocation, Long storeId) {
        Shop shop = shopRepository.findShopById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        Integer stock = itemRepository.countByShopId(storeId);
        return StoreInfoResponse.of(shop, stock, calculateDistance(myLocation, shop.getLatitude(), shop.getLongitude()));
    }

    public Integer calculateDistance(MyLocationRequest myLocation, Double x, Double y) {
        // 위도와 경도를 라디안 단위로 변환
        double x1Rad = Math.toRadians(myLocation.getX());
        double y1Rad = Math.toRadians(myLocation.getY());
        double x2Rad = Math.toRadians(x);
        double y2Rad = Math.toRadians(y);

        // 위도와 경도 차이
        double deltaLat = x2Rad - x1Rad;
        double deltaLon = y2Rad - y1Rad;

        // 하버 사인 공식 적용
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(x1Rad) * Math.cos(x2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 거리 계산
        return (int) Math.round(EARTH_RADIUS * c);
    }
}
