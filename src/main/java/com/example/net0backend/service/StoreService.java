package com.example.net0backend.service;

import com.example.net0backend.dto.request.MyLocationRequest;
import com.example.net0backend.dto.response.NearStoreResponse;
import com.example.net0backend.dto.response.StoreInfoResponse;
import com.example.net0backend.entity.Shop;
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

    public StoreInfoResponse getStoreInfo(Long storeId) {
        Shop shop = shopRepository.findShopById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        StoreInfoResponse storeInfoResponse = new StoreInfoResponse();
        storeInfoResponse.from(shop);
        return storeInfoResponse;
    }
}
