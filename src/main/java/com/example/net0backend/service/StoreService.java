package com.example.net0backend.service;

import com.example.net0backend.dto.request.MyLocationRequest;
import com.example.net0backend.dto.response.NearStoreResponse;
import com.example.net0backend.entity.Shop;
import com.example.net0backend.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final ShopRepository shopRepository;
    public List<NearStoreResponse> getNearStoreList(MyLocationRequest myLocation) {
        List<Shop> nearShopList = shopRepository.findNearShopList(myLocation.getX(), myLocation.getY());
        List<NearStoreResponse> nearStoreResponseList = new ArrayList<>();
        nearShopList.stream().forEach(shop -> {
            NearStoreResponse nearStoreResponse = new NearStoreResponse();
            nearStoreResponseList.add(nearStoreResponse.from(shop));
        });
        return nearStoreResponseList;
    }
}
